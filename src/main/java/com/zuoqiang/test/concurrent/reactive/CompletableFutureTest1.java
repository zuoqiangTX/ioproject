package com.zuoqiang.test.concurrent.reactive;

import java.util.concurrent.CompletableFuture;

/**
 * @author zuoqiang
 * @version 1.0
 * @description CompletableFuture
 * @date 2020/6/11 2:50 下午
 *
 * <p>
 * 使用Future获得异步执行结果时，要么调用阻塞方法get()，要么轮询看isDone()是否为true，
 * 这两种方法都不是很好，因为主线程也会被迫等待。
 * 1）创建一个CompletableFuture是通过CompletableFuture.supplyAsync()实现的
 * 2）CompletableFuture已经被提交给默认的线程池执行了，我们需要定义的是CompletableFuture完成时和异常时需要回调的实例。
 * <p>
 * 可见CompletableFuture的优点是：
 * <p>
 * 异步任务结束时，会自动回调某个对象的方法；
 * 异步任务出错时，会自动回调某个对象的方法；
 * 主线程设置好回调后，不再关心异步任务的执行。
 * </p>
 */

public class CompletableFutureTest1 {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureTest1::fetchPrice);
        System.out.println("不阻塞");
        // 如果执行成功:
        cf.thenAccept(result -> {
            System.out.println("price: " + result);
        });
        cf.exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    public static Double fetchPrice() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }
}
