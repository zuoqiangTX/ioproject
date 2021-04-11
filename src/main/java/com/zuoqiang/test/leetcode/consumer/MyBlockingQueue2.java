package com.zuoqiang.test.leetcode.consumer;

import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2021/4/12 1:42 上午
 */

public class MyBlockingQueue2 {
    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;
    private int maxSize;
    private LinkedList<Date> storage;

    public MyBlockingQueue2(int maxSize) {
        this.maxSize = maxSize;
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
        storage = new LinkedList<>();
    }

    private void take() throws InterruptedException {
        lock.lock();
        try {
            while (storage.size() == 0) {
                System.out.println("没啦");
                notEmpty.await();
            }
            System.out.println(storage.remove());
            notFull.signalAll();
        } finally {
            lock.unlock();

        }
    }

    public void put() throws InterruptedException {
        lock.lock();
        try {
            while (storage.size() == maxSize) {
                System.out.println("满啦");
                notFull.await();
            }
            storage.add(new Date());
            notEmpty.signalAll();
        } finally {
            lock.unlock();

        }
    }

    public static void main(String[] args) {
        MyBlockingQueue2 myBlockingQueue = new MyBlockingQueue2(10);
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    myBlockingQueue.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    myBlockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
