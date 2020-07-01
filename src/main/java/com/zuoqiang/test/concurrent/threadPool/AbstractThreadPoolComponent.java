package com.zuoqiang.test.concurrent.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 线程池通用组件
 * @date 2020/6/16 4:58 下午
 */

@Slf4j
public class AbstractThreadPoolComponent {
    protected static ThreadPoolExecutor tpe;

    /**
     * THREADPOOL INFO
     * io密集型
     */
    private final static int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    private final static int MAX_POOL_SIZE = CORE_POOL_SIZE;
    private final static long KEEP_ALIVE_TIME = 10L;
    private final static int QUEUE_SIZE = 5000;

    static {
        tpe = new
                ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(QUEUE_SIZE) {
                }, new

                ThreadFactory() {
                    private static final String THREAD_FORAMT = "异步通知银通-子线程-%d";
                    private final AtomicLong atomicLong = new AtomicLong();

                    @Override
                    public Thread newThread(Runnable r) {
                        String name = String.format(THREAD_FORAMT, atomicLong.getAndIncrement());
                        return new Thread(r, name);
                    }
                }, new

                RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        try {
                            executor.getQueue().put(r);
                        } catch (InterruptedException e) {
                            log.error(e.getMessage(), e);
                        }

                    }
                });
        tpe.allowCoreThreadTimeOut(true);
    }
}
