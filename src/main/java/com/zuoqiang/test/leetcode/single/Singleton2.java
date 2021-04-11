package com.zuoqiang.test.leetcode.single;

/**
 * @author zuoqiang
 * @version 1.0
 * @description Singleton
 * @date 2021/4/12 1:11 上午
 */

public class Singleton2 {
    private Singleton2 singleton;

    private Singleton2() {

    }

    public synchronized Singleton2 getObject() {

        if (singleton != null) {
            return singleton;
        }
        singleton = new Singleton2();
        return singleton;
    }
}
