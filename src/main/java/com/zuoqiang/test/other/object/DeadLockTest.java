package com.zuoqiang.test.other.object;

/**
 * @author zuo
 */
public class DeadLockTest {
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        new Thread(() -> {
            synchronized (a) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {

                }
                synchronized (b) {
                    System.out.println("xxx");
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (b) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {

                }
                synchronized (a) {
                    System.out.println("xxx");
                }
            }
        }).start();
    }
}
