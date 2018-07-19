import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ThreadPoolTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolTest.class);
    private static ExecutorService executorService;

    /**
     * 正在执行任务列表
     */
    private static final CopyOnWriteArrayList<Long> executingSnapshotList = new CopyOnWriteArrayList<Long>();

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

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            if (executingSnapshotList.addIfAbsent((long) i)) {
                //3、初始化任务镜像放入工作队列
                executorService.submit(new WorkerJob(i));
            }
        }
    }

    private static class WorkerJob implements Runnable {
        private int num;

        public WorkerJob(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            try {
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                //模拟任务执行1秒
                TimeUnit.SECONDS.sleep(1);
                if (num == 5) {
                    throw new RuntimeException("内部任务错误");
                } else if (num == 7) {
                    int a = 1 / 0;
                }
                stopWatch.stop();
                LOGGER.info("成功执行任务" + num + "耗时: " + stopWatch.getTotalTimeSeconds() + "s");
            } catch (Exception e) {
                LOGGER.error("任务" + num + "执行报错！err = " + e.getMessage());
            }
            //列表去掉正在执行的任务
            executingSnapshotList.remove(Long.valueOf(num));
        }
    }
}
