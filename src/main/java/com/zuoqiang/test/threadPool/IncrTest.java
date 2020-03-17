package com.zuoqiang.test.threadPool;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zuoqiang
 * @version 1.0
 * @description CountDownLatch 模拟并发测试
 * @date 2020/3/17 4:56 下午
 */


public class IncrTest {

    public static void main(String[] args) throws InterruptedException {
        int currentCount = 1000;
        concurrenceTest(currentCount);
    }

    public static void concurrenceTest(int currentCount) {
        /**
         * 模拟高并发情况代码
         */
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final CountDownLatch countDownLatch = new CountDownLatch(currentCount); // 相当于计数器，当所有都准备好了，再一起执行，模仿多并发，保证并发量
        final CountDownLatch countDownLatch2 = new CountDownLatch(currentCount); // 保证所有线程执行完了再打印atomicInteger的值
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        try {
            for (int i = 0; i < currentCount; i++) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            countDownLatch.await(); //一直阻塞当前线程，直到计时器的值为0,保证同时并发
                            getTestMethod();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //每个线程增加1000次，每次加1
                        for (int j = 0; j < currentCount; j++) {
                            atomicInteger.incrementAndGet();
                        }
                        countDownLatch2.countDown();
                    }

                    private void getTestMethod() {
                        System.out.printf("并发同时调用实现自己的测试方法:%s %n", new Date().toString());
                    }
                });
                countDownLatch.countDown();
            }

            countDownLatch2.await();// 保证所有线程执行完
            System.out.println(atomicInteger);
            executorService.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
