package com.zuoqiang.test.io.socket.bio2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * SocketIO 自定义线程处理器（伪异步IO,采用线程池和队列解决问题）
 *
 * @author zuoqiang
 */
public class HandlerExecutorPool {
    private ExecutorService executorService;

    public HandlerExecutorPool(int maxpoolSize, int queueSize) {
        //有界队列
        this.executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                maxpoolSize,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void execute(Runnable task) {
        this.executorService.execute(task);
    }
}
