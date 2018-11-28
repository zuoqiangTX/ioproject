import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 同步和异步时间测试
 * <p>
 * 同步 一直阻塞，主流程时间 = 准备时间（10s） + work(30s) = 40s
 * 异步 提交异步线程以后，主线程继续执行，主流程时间 = 准备时间（10s） =10s
 * </p>¬
 * <p>
 * 总结：mq中如果有比较耗时间的任务，建议采用异步提交任务的方式，避免消费时间过长导致mq重发消息，重新消费
 * </p>
 *
 * @author baiyue
 */
public class AsynAndSynTest {
    private static Logger logger = LoggerFactory.getLogger(AsynAndSynTest.class);


    /**
     * 线程池配置
     */
    public final static int CORE_POOL_SIZE = 1;
    public final static int MAX_POOL_SIZE = 1;
    public final static long KEEP_ALIVE_TIME = 30L;
    public final static int QUEUE_SIZE = 1000;


    private static ExecutorService executor = new ThreadPoolExecutor(
            AsynAndSynTest.CORE_POOL_SIZE,
            AsynAndSynTest.MAX_POOL_SIZE,
            AsynAndSynTest.KEEP_ALIVE_TIME,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(AsynAndSynTest.QUEUE_SIZE),
            new ThreadFactory() {
                private static final String THREAD_FORAMT = "AsynAndSynTest-pool-%d";
                private final AtomicInteger atomicInteger = new AtomicInteger();

                @Override
                public Thread newThread(Runnable r) {
                    String name = String.format(THREAD_FORAMT, atomicInteger.getAndIncrement());
                    return new Thread(r, name);
                }
            }, new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                logger.error("AsycAndsysTest队列执行出错!", e);
            }
        }
    });

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        // 异步方法
        aysn();
        // 同步方法
//        syn();
        long endTime = System.currentTimeMillis();
        System.out.println("主流程耗时为：" + (endTime - startTime) / 1000 + "s");
        TimeUnit.SECONDS.sleep(60 * 2);
    }

    /**
     * 同步方法
     */
    private static void syn() {
        prepare();
        work();
    }

    /**
     * 异步方法
     */
    private static void aysn() {
        prepare();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                work();
            }
        });

    }

    /**
     * 工作线程的工作
     */
    public static void work() {
        try {
            System.out.println("工作开始！");
            TimeUnit.SECONDS.sleep(30);
            System.out.println("工作结束！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 主线程的准备工作
     */
    public static void prepare() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
