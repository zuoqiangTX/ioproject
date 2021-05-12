package com.zuoqiang.test.thredlocal;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author zuoqiang
 * @version 1.0
 * @description hreadLocal、InheritableThreadLocal的最大局限性就是：无法为预先创建好（未投入使用）的线程实例传递变量（准确来说是首次传递某些场景是可行的，
 * 而后面由于线程池中的线程是复用的，无法进行更新或者修改变量的传递值），泛线程池Executor体系、TimerTask和ForkJoinPool等一般会预先创建（核心）线程，
 * 也就它们都是无法在线程池中由预创建的子线程执行的Runnable任务实例中使用。
 * @date 2021/5/12 5:24 下午
 */

public class InheritableThreadForExecutor {
    static final InheritableThreadLocal<String> ITL = new InheritableThreadLocal<>();
    static final Executor EXECUTOR = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws Exception {
        ITL.set("throwable");
        System.out.println("父线程的变量为" + ITL.get());
        EXECUTOR.execute(() -> {
            System.out.println(String.format("线程池子线程%s的变量为%s", Thread.currentThread().getName(), ITL.get()));
        });
        ITL.set("doge");
        System.out.println("父线程的变量为" + ITL.get());
        EXECUTOR.execute(() -> {
            System.out.println(String.format("线程池子线程%s的变量为%s", Thread.currentThread().getName(), ITL.get()));
        });
//        TimeUnit.SECONDS.sleep(5);
    }
}
