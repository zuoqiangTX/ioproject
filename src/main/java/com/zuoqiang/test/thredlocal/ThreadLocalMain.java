package com.zuoqiang.test.thredlocal;

import java.util.concurrent.TimeUnit;

/**
 * @author zuoqiang
 * @version 1.0
 * @description ThreadLocal：单个线程生命周期强绑定，只能在某个线程的生命周期内对ThreadLocal进行存取，不能跨线程存取。
 * @date 2021/5/12 5:20 下午
 */

public class ThreadLocalMain {
    private static ThreadLocal<String> TL = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            methodFrame1();
        }, "childThread").start();
        TimeUnit.SECONDS.sleep(5);
    }

    private static void methodFrame1() {
        TL.set("throwable");
        methodFrame2();
    }

    private static void methodFrame2() {
        System.out.println(TL.get());
    }
}
