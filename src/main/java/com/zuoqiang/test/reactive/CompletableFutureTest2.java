package com.zuoqiang.test.reactive;

import java.util.concurrent.CompletableFuture;

/**
 * @author zuoqiang
 * @version 1.0
 * @description CompletableFuture可以串行执行
 * @date 2020/6/11 2:55 下午
 * <p>
 * 定义两个CompletableFuture，第一个CompletableFuture根据证券名称查询证券代码，
 * 第二个CompletableFuture根据证券代码查询证券价格，
 * 这两个CompletableFuture实现串行操作如下。
 * </P>
 */

public class CompletableFutureTest2 {
    public static void main(String[] args) throws InterruptedException {
        // 第一个任务:
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油");
        });
        // cfQuery成功后继续执行下一个任务:
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice(code);
        });
        // cfFetch成功后打印结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    static String queryCode(String name) {
        try {
            Thread.sleep(100);
            System.out.println(name);
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code) {
        try {
            Thread.sleep(100);
            System.out.println(code);
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }
}
