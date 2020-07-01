package com.zuoqiang.test.tools.guava;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zuoqiang
 * @version 1.0
 * @description ListenableFuture
 * <p>
 * ListenableFuture顾名思义就是可以监听的Future，它是对java原生Future的扩展增强。
 * 我们知道Future表示一个异步计算任务，当任务完成时可以得到计算结果。
 * 如果我们希望一旦计算完成就拿到结果展示给用户或者做另外的计算，
 * 就必须使用另一个线程不断的查询计算状态。这样做，代码复杂，而且效率低下。
 * 使用ListenableFuture Guava帮我们检测Future是否完成了，如果完成就自动调用回调函数，这样可以减少并发程序的复杂度。
 * </p>
 * <p>
 * 另外ListenableFuture还有其他几种内置实现：
 * SettableFuture：不需要实现一个方法来计算返回值，而只需要返回一个固定值来做为返回值，可以通过程序设置此Future的返回值或者异常信息
 * CheckedFuture： 这是一个继承自ListenableFuture接口，他提供了checkedGet()方法，此方法在Future执行发生异常时，可以抛出指定类型的异常。
 * </P>
 * @date 2020/5/8 11:03 上午
 */

public class ListenableFutureDemo {
    public static void main(String[] args) throws InterruptedException {
//        testRateLimiter();
        //同步获取结果
        testListenableFuture();
        //异步获取结果 自己起一个线程来查询状态
        testAsyncByMine();
        //异步直接采用ListenableFuture方法
        testAsyncByGuava();
    }

    private static void testAsyncByGuava() {
        ListeningExecutorService executorService = MoreExecutors
                .listeningDecorator(Executors.newCachedThreadPool());
        final ListenableFuture<Integer> listenableFuture = executorService
                .submit(new Task("testListenableFuture"));
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.out
                        .println("调用回调得到结果 " + result);
                executorService.shutdown();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        }, Executors.newSingleThreadExecutor());
        System.out.println("证明程序没有阻塞哦");
    }

    private static void testAsyncByMine() {
        ListeningExecutorService executorService = MoreExecutors
                .listeningDecorator(Executors.newCachedThreadPool());
        final ListenableFuture<Integer> listenableFuture = executorService
                .submit(new Task("testListenableFuture"));
        listenableFuture.addListener(() -> {
            try {
                System.out.println("自己启动另外一个线程不断的查询计算状态>>>listenable future's result "
                        + listenableFuture.get());
                executorService.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, executorService);
        System.out.println("证明程序没有阻塞哦");
    }


    private static void testListenableFuture() {
        ListeningExecutorService executorService = MoreExecutors
                .listeningDecorator(Executors.newCachedThreadPool());
        final ListenableFuture<Integer> listenableFuture = executorService
                .submit(new Task("testListenableFuture"));
        //同步获取调用结果
        try {
            //结果没出来之前会一直阻塞
            System.out.println("task 结果为:" + listenableFuture.get());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        System.out.println("证明程序在阻塞，因为结果总是慢于前面的get语句");
        executorService.shutdown();
    }

    /**
     * RateLimiter类似于JDK的信号量Semphore，他用来限制对资源并发访问的线程数
     */
    private static void testRateLimiter() throws InterruptedException {
        ListeningExecutorService executorService = MoreExecutors
                .listeningDecorator(Executors.newCachedThreadPool());
        RateLimiter limiter = RateLimiter.create(5.0); // 每秒不超过4个任务被提交
        for (int i = 0; i < 10; i++) {
            // 请求RateLimiter, 超过permits会被阻塞)
            double waitTime = limiter.acquire();
            final ListenableFuture<Integer> listenableFuture = executorService
                    .submit(new Task("is " + i + "wait time:" + waitTime));

        }

    }
}


class Task implements Callable<Integer> {
    String str;

    public Task(String str) {
        this.str = str;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("计算结果..." + str);
        TimeUnit.SECONDS.sleep(2);
        return 7;

    }
}
