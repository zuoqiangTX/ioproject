package com.zuoqiang.test.other;

import com.zuoqiang.test.design.producer.BlockingQueue;

import java.util.concurrent.TimeUnit;

public class ThreadTest {
    public static void main(String[] args) {
        BlockingQueue queue = new BlockingQueue(3);
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.put(new Object());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
