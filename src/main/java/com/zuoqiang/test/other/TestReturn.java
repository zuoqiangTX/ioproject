package com.zuoqiang.test.other;

import java.util.concurrent.TimeUnit;

/**
 * 测试return方法
 * <p>
 * Java中的return有两方面的用途：
 * <p>
 * （1）返回方法指定类型的值（前提是方法的返回值类型不是void）。
 * <p>
 * （2）方法的结束，它会导致当前的方法退出。
 * <p>
 *  
 * <p>
 * 使用的两种形式：
 * <p>
 * （1）方法有返回值类型，格式：
 * <p>
 * return 返回值;
 * <p>
 * （2）方法没返回值类型，格式：
 * <p>
 * return;
 * ---------------------
 * 作者：睡觉也能赚钱
 * 来源：CSDN
 * 原文：https://blog.csdn.net/Tszching_Leung/article/details/80686022
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 *
 * @author baiyue
 */
public class TestReturn {
    public static void main(String[] args) {
        int[] array = {};
//        test(array);
//        new Thread(new Task(array)).start();
        //return 可以直接结束当前程序段
        if (testBoolean(array)) {
            System.out.println("程序结束🌶");
            return;
        }
        System.out.println("开始执行程序");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行完毕！");
    }

    /**
     * return 可以直接结束方法
     *
     * @param arr
     */
    static void test(int[] arr) {//声明为static方法是为了可以在主方法中使用
        //判断数组是否为空
        if (arr == null || arr.length == 0) {
            System.out.print("数组为空");
            return;//直接退出当前方法，后面的不执行
        }
        System.out.println("数组不为空");//数组为空return直接退出本方法语句没有执行
    }

    static class Task implements Runnable {
        private int[] arr;

        public Task(int[] arr) {
            this.arr = arr;
        }

        @Override
        public void run() {
            /**
             * runable中return也可以结束程序哦！
             */
            if (arr == null || arr.length == 0) {
                System.out.println("方法结束！");
                return;
            }
            System.out.println("开冲！");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行完毕！");
        }
    }

    static boolean testBoolean(int[] arr) {//声明为static方法是为了可以在主方法中使用
        //判断数组是否为空
        if (arr == null || arr.length == 0) {
            System.out.print("数组为空");
            return true;
        }
        return false;
    }
}
