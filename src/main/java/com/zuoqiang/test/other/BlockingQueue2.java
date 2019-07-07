package com.zuoqiang.test.other;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue2 {

    private final Object lock = new Object();
    private LinkedList<Object> lists = new LinkedList<Object>();
    //容量
    private int cap;

    public BlockingQueue2(int cap) {
        this.cap = cap;
    }

    //消费
    public void get() throws InterruptedException {
        synchronized (lock) {
            while (lists.size() == 0) {
                //没有数据
                System.out.println("没有data，等待生产");
                lock.wait();
            }
            Object o = lists.poll();
            System.out.println("取到了o:%s" + o);
            //通知生产者生产
            lock.notifyAll();
        }
    }

    //生产
    public void put(Object e) throws InterruptedException {
        synchronized (lock) {

            while (lists.size() == cap) {
                //满了
                System.out.println("data太满，等待消费");
                lock.wait();
            }
            //不满加入
            lists.add(e);
            System.out.println("加入e:%s" + e);
            //通知消费者消费
            lock.notifyAll();
        }
    }

    public static void main(String[] args) {
        BlockingQueue2 queue = new BlockingQueue2(3);
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
