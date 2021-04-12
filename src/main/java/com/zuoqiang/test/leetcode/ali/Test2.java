package com.zuoqiang.test.leetcode.ali;

import java.util.concurrent.CountDownLatch;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 第二题
 * @date 2021/4/12 11:00 上午
 */

public class Test2 {

    static class Task1 implements Runnable {
        private CountDownLatch s;

        public Task1(CountDownLatch s) {
            this.s = s;
        }

        @Override
        public void run() {
            System.out.println("A");
            s.countDown();
        }
    }

    static class Task2 implements Runnable {
        private CountDownLatch s1;
        private CountDownLatch s2;

        public Task2(CountDownLatch s1, CountDownLatch s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        public void run() {
            try {
                s1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
            s2.countDown();
        }
    }

    static class Task3 implements Runnable {
        private CountDownLatch s;

        public Task3(CountDownLatch s) {
            this.s = s;
        }

        @Override
        public void run() {
            try {
                s.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("C");
        }
    }

    public static void main(String[] args) {
        CountDownLatch s1 = new CountDownLatch(1);
        CountDownLatch s2 = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(new Task1(s1)).start();
            new Thread(new Task2(s1, s2)).start();
            new Thread(new Task3(s2)).start();

        }
    }
}
