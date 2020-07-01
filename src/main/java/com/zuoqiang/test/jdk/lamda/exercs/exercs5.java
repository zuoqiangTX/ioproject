package com.zuoqiang.test.jdk.lamda.exercs;

/**
 * 线程  lamda
 *
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-06 22:28
 */
public class exercs5 {
    public static void main(String[] args) {
        //开辟一条线程
//        Thread t =new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("test!");
//            }
//        });

        Thread t = new Thread(() -> {
            System.out.println("test");
        });
        t.start();

    }
}
