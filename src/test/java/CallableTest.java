import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CallableTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CallableTest.class);
    private static ExecutorService executorService;
    /**
     * THREADPOOL INFO
     */
    private final static int CORE_POOL_SIZE = 5;
    private final static int MAX_POOL_SIZE = 10;
    private final static long KEEP_ALIVE_TIME = 30L;
    private final static int QUEUE_SIZE = 2000;

    private static ThreadFactory factory = new ThreadFactory() {
        private final AtomicInteger COUNT = new AtomicInteger();

        public Thread newThread(Runnable r) {
            return new Thread(r, String.format("线程池-第%s线程", COUNT.getAndIncrement()));
        }
    };


    static {
        executorService = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(QUEUE_SIZE), factory
        );
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                executorService.shutdown();
                LOGGER.info("{}关闭线程池！", Thread.currentThread().getName());
            }
        }, String.format("ThreadPoolExecutor-ShutdownHook")));
        ((ThreadPoolExecutor) executorService).allowCoreThreadTimeOut(true);
    }

    public static void main(String[] args) {
        LOGGER.info("任务开始...");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int successCount = 0;
        int failCount = 0;
        List<FutureTask<Boolean>> futureTaskList = new ArrayList<FutureTask<Boolean>>();
        for (int i = 0; i < 10; i++) {
            WorkerJob job = new WorkerJob(i);
            FutureTask<Boolean> futureTask = new FutureTask<Boolean>(job);
            futureTaskList.add(futureTask);
            executorService.submit(futureTask);
        }

        //统计任务的成功与数量
        for (int i = 0; i < futureTaskList.size(); i++) {
            FutureTask<Boolean> futureTask = futureTaskList.get(i);
            try {
                Boolean sendFlag = futureTask.get();
                if (sendFlag) {
                    successCount++;
                } else {
                    failCount++;
                }
            } catch (Exception e) {
                LOGGER.error("任务执行错误", e);
            }
        }
        stopWatch.stop();
        LOGGER.info("任务结束...处理时间{},成功数量{},失败数量{}", stopWatch.getTime(), successCount, failCount);

    }

    private static class WorkerJob implements Callable<Boolean> {
        private int num;

        public WorkerJob(int num) {
            this.num = num;
        }

        @Override
        public Boolean call() throws Exception {
            Boolean flag = true;
            //模拟任务执行1秒
            TimeUnit.MILLISECONDS.sleep(1000);
            if (num == 2) {
                throw new RuntimeException(String.format("{}:任务{}内部任务错误", Thread.currentThread().getName(), num));
            } else if (num == 7) {
                int a = 1 / 0;
            }
            LOGGER.info("{}:任务{}执行成功", Thread.currentThread().getName(), num);
            return flag;

        }
    }
}
