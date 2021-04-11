package com.zuoqiang.test.leetcode.consumer;

import java.util.LinkedList;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 队列
 * @date 2021/4/12 1:28 上午
 */

public class MyBlockingQueue {
    private int maxSize;
    private LinkedList<Object> linkedList;

    public MyBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
        this.linkedList = new LinkedList();
    }

    public synchronized void take() throws InterruptedException {
        while (linkedList.size() == 0) {
            System.out.println("没啦");
            wait();
        }
        System.out.println(linkedList.remove());
        notifyAll();
    }

    public synchronized void put() throws InterruptedException {
        while (linkedList.size() == maxSize) {
            System.out.println("满啦");
            wait();
        }
        linkedList.add(new Object());
        notifyAll();
    }

    public static void main(String[] args) {
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue(10);
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
