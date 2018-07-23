package com.zuoqiang.test.threadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTest.class);
    private static ExecutorService executorService;
    /**
     * THREADPOOL INFO
     */
    private final static int CORE_POOL_SIZE = 5;
    private final static int MAX_POOL_SIZE = 10;
    private final static long KEEP_ALIVE_TIME = 30L;
    private final static int QUEUE_SIZE = 2000;

    static {
        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNameFormat("rpc-pool-%d").build();
        executorService = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(QUEUE_SIZE),
//                nameThreadFactory,   //采用谷歌的这个ThreadFactory也可以,效果和自定义的差不多
                new ThreadFactory() {
                    private static final String THREAD_FORAMT = "ArTaskThread-%d";
                    private final AtomicInteger atomicInteger = new AtomicInteger();

                    @Override
                    public Thread newThread(Runnable r) {
                        String name = String.format(THREAD_FORAMT, atomicInteger.getAndIncrement());
                        return new Thread(r, name);
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        try {
                            executor.getQueue().put(r);
                        } catch (InterruptedException e) {
                            LOGGER.error("线程被中断了！", e);
                        }
                    }
                });
        ((ThreadPoolExecutor) executorService).allowCoreThreadTimeOut(true);
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        //结果处理器
        AbstractResultConsumer<Boolean, Counter> abstractResultConsumer = new DefaultResultConsumer(counter);
        //线程池
        EnhanceCompletionService<Boolean, Counter> ecs = new EnhanceCompletionService<>(executorService, abstractResultConsumer);
        //任务创建者
        AbstractTaskProvider provider = new TestProducer(ecs);
        LOGGER.info("任务开始...");
        ecs.execute(provider);
        LOGGER.info("总数量为{},成功数量{},失败数量{}...", counter.getAll(), counter.getSuccess(), counter.getFail());
    }

    static class TestProducer extends AbstractTaskProvider {
        public TestProducer(EnhanceCompletionService ecs) {
            super(ecs);
        }

        @Override
        public void offerTasks() {
            for (int i = 0; i < 100; i++) {
                //添加任务
                ecs.submit(new Task(i));
            }
        }

        class Task implements Callable<Boolean> {
            private int i;

            public Task(int i) {
                this.i = i;
            }

            @Override
            public Boolean call() throws Exception {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    if (i < 50) {
                        throw new RuntimeException("运行错误");
                    }
                    LOGGER.info("任务{}执行成功！", i);
                    return true;
                } catch (Exception e) {
                    LOGGER.error("任务{}执行失败！", i);
                    return false;
                }
            }
        }
    }

}
