package com.zuoqiang.test.thredlocal;

import java.util.concurrent.TimeUnit;

/**
 * @author zuoqiang
 * @version 1.0
 * @description InheritableThreadLocal：（1）可以无感知替代ThreadLocal的功能，当成ThreadLocal使用。
 * （2）明确父-子线程关系的前提下，继承（拷贝）父线程的线程本地变量缓存过的变量，而这个拷贝的时机是子线程Thread实例化时候进行的，
 * 也就是子线程实例化完毕后已经完成了InheritableThreadLocal变量的拷贝，这是一个变量传递的过程。
 * @date 2021/5/12 5:21 下午
 */

public class InheritableThreadLocalMain {
    // 此处可以尝试替换为ThreadLocal，最后会输出null
    static InheritableThreadLocal<String> ITL = new InheritableThreadLocal<>();

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            // 在父线程中设置变量
            ITL.set("throwable");
            System.out.println("父线程的变量为" + ITL.get());
            new Thread(() -> {
                methodFrame1();
            }, "childThread").start();
        }, "parentThread").start();
        TimeUnit.SECONDS.sleep(5);
    }

    private static void methodFrame1() {
        methodFrame2();
    }

    private static void methodFrame2() {
        System.out.println("子线程的变量为" + ITL.get());
    }
}
