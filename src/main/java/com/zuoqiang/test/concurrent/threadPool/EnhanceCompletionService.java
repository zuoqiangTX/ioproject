package com.zuoqiang.test.concurrent.threadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 增强完成服务<br/>
 * {@link ExecutorCompletionService} 的一个抽象增强服务。
 * <p>
 * jdk自带的{@link ExecutorCompletionService} 是一个支持提交一批任务到{@link Executor}中，从而可以以多线程的方式处理这一批任务，
 * 提供一个{@link ExecutorCompletionService#take()}方法，可以从多线程执行的结果队列里边，取出已经执行完毕的结果，
 * 并对结果做处理。这样一种多个线程处理数据，并由一个线程消费结果数据的方式，可以提升批量任务处理时的性能。
 * <p>
 * {@link EnhanceCompletionService} 是对 {@link ExecutorCompletionService}的一个抽象增强，
 * 主要针对一些系统JOB遍历处理表数据时，可以通用的一个多线程模式。该服务需要在创建时指定一个用来多线程处理任务的{@link ThreadPoolExecutor}，
 * 所有提交到该服务的任务将在该线程池中并发执行。
 * <p>
 * 需要定义一个{@link AbstractTaskProvider}的实现类，用于查询数据库中的数据并通过{@link EnhanceCompletionService#submit(Callable)}
 * 方法提交。
 * <p>
 * 需要定义一个{@link AbstractResultConsumer}的实现类，用于从结果队列中查询业务数据的处理结果，并对结果做汇总合并，该实现类是单线程处理，
 * 所以不需要担心并发消费的问题。
 * <p>
 * <p>
 * 使用方式:<br/>
 * <pre>
 * // 1. 定义线程池，可以是临时或者共享池
 * ThreadPoolExecutor tpe = new ThreadPoolExecutor(.....);
 * // 2. 实现结果处理器 AbstractResultHandler
 * AbstractResultConsumer< V, S> resultConsumer = new AbstractResultConsumer< V, S>() {......};
 * // 3. 创建增强完成服务
 * EnhanceCompletionService< V, S> ecs = new EnhanceCompletionService< V, S>(tpe, resultConsumer);
 * // 4. 实现任务提交者，用于提交任务到增强服务
 * AbstractTaskProvider provider = new AbstractTaskProvider(ecs){.....};
 * // 5. 开始执行
 * S result = ecs.execute(provider);
 * </pre>
 * <p>
 * 注意事项:
 * <li>{@link AbstractTaskProvider}在提交任务到增强服务时，必须使用{@link EnhanceCompletionService#submit(Callable)}方法提交</li>
 * <li>增强完成服务只适合适用于一次性使用的场景，如JOB，临时任务等，不允许作为内存对象长期持有</li>
 *
 * @param <V> 单条数据处理结果类型
 * @param <S> 所有V的汇总结果
 * @author zuoqiang
 * @version APP 6.6 (Fund aip)
 * @see AbstractTaskProvider
 * @see AbstractResultConsumer
 * @since 2018.07.23
 */
public class EnhanceCompletionService<V, S> {

    private static final Logger logger = LoggerFactory.getLogger(EnhanceCompletionService.class);

    /**
     * 执行任务的完成服务
     */
    private ExecutorCompletionService<V> ecs;

    /**
     * 是否所有任务已经全部提交到线程池
     */
    private AtomicBoolean isAllTaskSubmitted = new AtomicBoolean(false);

    private ReentrantLock lock = new ReentrantLock();

    /**
     * 消费任务是否允许开始消费
     */
    private Condition consumerCondition = lock.newCondition();

    /**
     * 还在处理的任务总量
     */
    private AtomicInteger dealingTaskCount = new AtomicInteger(0);

    /**
     * 结果处理器
     */
    private AbstractResultConsumer<V, S> resultConsumer;

    public EnhanceCompletionService(Executor executor, AbstractResultConsumer<V, S> resultConsumer) {
        this.ecs = new ExecutorCompletionService<V>(executor, new LinkedBlockingDeque<Future<V>>());
        this.resultConsumer = resultConsumer;
    }

    /**
     * 提交单个任务到完成服务
     */
    public void submit(Callable<V> task) {
        ecs.submit(task);
        dealingTaskCount.incrementAndGet();
        notifyFutureConsumerThread();
    }

    /**
     * 开始任务并执行
     *
     * @param taskProvider 任务提供者
     */
    public synchronized S execute(AbstractTaskProvider taskProvider) {

        /*
         * 启动消费任务
         */
        Thread futureConsumerThread = new CompletionQueueConsumerTask(resultConsumer);
        futureConsumerThread.start();

        try {
            /*
             * execute 方法在调用程序主线程中运行
             * 即提交任务线程
             */
            taskProvider.offerTasks();

            //设置任务标识为,全部提交
            isAllTaskSubmitted.compareAndSet(false, true);

            //通知消费线程从阻塞的地方继续进行
            notifyFutureConsumerThread();

            /*
             * 等待结果消费线程被回收（执行完毕）
             */
            futureConsumerThread.join();

        } catch (Throwable e) {
            logger.error("-EnhanceCompletionService执行任务异常", e);
        } finally {
            notifyFutureConsumerThread();
        }

        return resultConsumer.getResult();
    }

    /**
     * 唤醒结果消费线程的等待状态
     */
    private void notifyFutureConsumerThread() {
        lock.lock();
        try {
            consumerCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 处理结果队列消费任务
     */
    class CompletionQueueConsumerTask extends Thread {

        private AbstractResultConsumer<V, S> resultHandler;

        CompletionQueueConsumerTask(AbstractResultConsumer<V, S> resultHandler) {
            this.resultHandler = resultHandler;
        }

        @Override
        public void run() {

            while (true) {

                //如果所有任务已经全部提交到线程池 或者还在处理的任务数量为0，跳出循环
                if (isAllTaskSubmitted.get() && dealingTaskCount.get() <= 0) {
                    break;
                }

                lock.lock();
                try {
                    //如果所有任务没有全部提交到线程池并且还在处理的任务数量为0，线程阻塞在这里
                    if (!isAllTaskSubmitted.get() && dealingTaskCount.get() <= 0) {
                        consumerCondition.await();
                    }
                } catch (InterruptedException e) {
                    logger.warn("CompletionQueueConsumerTask增强服务结果消费线程在消费等待时发生InterruptedException", e);
                } finally {
                    lock.unlock();
                }

                int count = dealingTaskCount.get();

                for (int i = 0; i < count; i++) {
                    try {
                        //获取ExecutorService中的任务结果，并放入consume包装结果
                        V v = ecs.take().get();
                        resultHandler.consume(v);
                    } catch (Throwable e) {
                        logger.error(e.getMessage(), e);
                    } finally {
                        dealingTaskCount.decrementAndGet();
                    }
                }

            }
        }
    }
}
