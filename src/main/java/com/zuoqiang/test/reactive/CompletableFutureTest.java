package com.zuoqiang.test.reactive;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author zuoqiang
 * @version 1.0
 * @description CompletableFuture测试【异步编程】
 * <p>
 * 在Java中CompletableFuture用于异步编程，异步编程是编写非阻塞的代码，运行的任务在一个单独的线程，与主线程隔离，并且会通知主线程它的进度，成功或者失败。
 * <p>
 * 在这种方式中，主线程不会被阻塞，不需要一直等到子线程完成。主线程可以并行的执行其他任务。
 * <p>
 * 使用这种并行方式，可以极大的提高程序的性能。
 * @date 2020/6/16 4:07 下午
 */

public class CompletableFutureTest {
    public static void main(String[] args) throws Exception {
//        createCompletableFuture();
//        notHaveReturnValue();
        HaveReturnValue();
        return;
    }

    /**
     * supplyAsync() 运行一个异步任务并且返回结果
     *
     * @throws Exception
     */
    private static void HaveReturnValue() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        });
        System.out.println(future.get());
    }

    /**
     * 使用 runAsync() 运行异步计算
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void notHaveReturnValue() throws InterruptedException, ExecutionException {
        // Run a task specified by a Runnable Object asynchronously.
//        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
//            @Override
//            public void run() {
//                // Simulate a long-running Job
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    throw new IllegalStateException(e);
//                }
//                System.out.println("I'll run in a separate thread than the main thread.");
//            }
//        });
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            // Simulate a long-running Job
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("I'll run in a separate thread than the main thread.");
        });

        // Block and wait for the future to complete
        future.get();
    }

    /**
     * 创建
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void createCompletableFuture() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = new CompletableFuture<String>();
        completableFuture.complete("1");
        String result = completableFuture.get();
        System.out.println("不阻塞的话在这里");
    }
}
