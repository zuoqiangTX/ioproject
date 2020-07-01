package com.zuoqiang.test.concurrent.reactive;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 1)CompletableFuture.get()方法是阻塞的。它会一直等到Future完成并且在完成后返回结果。
 * 2)附上一个回调给CompletableFuture，当Future完成的时候，自动的获取结果
 * 可以使用 thenApply(), thenAccept() 和thenRun()方法附上一个回调给CompletableFuture。
 * @date 2020/6/16 4:25 下午
 */

public class ThenApplyTest {
    public static void main(String[] args) throws Exception {
//        可以使用 thenApply() 处理和改变CompletableFuture的结果。
//        transfer();
//        附加一系列的thenApply()在回调方法 在CompletableFuture写一个连续的转换。
        link();
    }

    private static void link() throws InterruptedException, java.util.concurrent.ExecutionException {
        CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        }).thenApply(name -> {
            return "Hello " + name;
        }).thenApply(greeting -> {
            return greeting + ", Welcome to the CalliCoder Blog";
        });

        System.out.println(welcomeText.get());
// Prints - Hello Rajeev, Welcome to the CalliCoder Blog
    }

    private static void transfer() throws InterruptedException, java.util.concurrent.ExecutionException {
        // Create a CompletableFuture
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        });
        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
            return "Hello " + name;
        });
        // Block and get the result of the future.
        System.out.println(greetingFuture.get()); // Hello Rajeev
    }
}
