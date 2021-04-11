package com.zuoqiang.test.leetcode.single;

/**
 * @author zuoqiang
 * @version 1.0
 * @description Singleton
 * @date 2021/4/12 1:11 上午
 */

public class Singleton {
    private static Singleton singleton = new Singleton();

    private Singleton() {

    }

    public static Singleton getObject() {
        return singleton;
    }
}
