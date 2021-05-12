package com.zuoqiang.test.thredlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 线程池中设置ttl
 * @date 2021/5/12 5:32 下午
 */

public class TtlSample2 {
    static TransmittableThreadLocal<String> TTL = new TransmittableThreadLocal<>();
    static final Executor EXECUTOR = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws Exception {
        TTL.set("throwable");
        System.out.println(String.format("父线程%s的变量为%s", Thread.currentThread().getName(), TTL.get()));
        EXECUTOR.execute(TtlRunnable.get(() -> {
            System.out.println(String.format("线程池子线程%s的变量为%s", Thread.currentThread().getName(), TTL.get()));
        }));
        TTL.set("doge");
        System.out.println(String.format("父线程%s的变量为%s", Thread.currentThread().getName(), TTL.get()));
        EXECUTOR.execute(TtlRunnable.get(() -> {
            System.out.println(String.format("线程池子线程%s的变量为%s", Thread.currentThread().getName(), TTL.get()));
        }));
        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
    }
}
