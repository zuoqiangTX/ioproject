package com.zuoqiang.test.thredlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.TimeUnit;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 父子线程
 * @date 2021/5/12 5:31 下午
 */

public class TtlSample1 {
    static TransmittableThreadLocal<String> TTL = new TransmittableThreadLocal<>();

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            // 在父线程中设置变量
            TTL.set("throwable");
            new Thread(TtlRunnable.get(() -> {
                methodFrame1();
            }), "childThread").start();
        }, "parentThread").start();
        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
    }

    private static void methodFrame1() {
        methodFrame2();
    }

    private static void methodFrame2() {
        System.out.println(String.format("线程池子线程%s的变量为%s", Thread.currentThread().getName(), TTL.get()));
    }
}
