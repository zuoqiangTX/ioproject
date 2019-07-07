package com.zuoqiang.test.other;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue {

    private ReentrantLock lock = new ReentrantLock();
    private Condition notifyProducer = lock.newCondition();
    private Condition notifyConsumer = lock.newCondition();
     private LinkedList<Object> lists = new LinkedList<Object>();
    //容量
    private int cap;

    public BlockingQueue(int cap) {
        this.cap = cap;
    }

    //消费
    public void get() throws InterruptedException {
        try {
            lock.lock();
            while (lists.size() == 0) {
                //没有数据
                System.out.println("没有data，等待生产");
                notifyConsumer.await();
            }
            Object o = lists.poll();
            System.out.println("取到了o:%s" + o);
            //通知生产者生产
            notifyProducer.signalAll();
        } finally {
            lock.unlock();
        }

    }

    //生产
    public void put(Object e) throws InterruptedException {
        try {
            lock.lock();
            while (lists.size() == cap) {
                //满了
                System.out.println("data太满，等待消费");
                notifyProducer.await();
            }
            //不满加入
            lists.add(e);
            System.out.println("加入e:%s" + e);
            //通知消费者消费
            notifyConsumer.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
