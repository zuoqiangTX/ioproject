package com.zuoqiang.test.leetcode.single;

/**
 * @author zuoqiang
 * @version 1.0
 * @description Singleton
 * @date 2021/4/12 1:11 上午
 */

public class Singleton4 {

    private Singleton4() {

    }

    public static class SingletonHolder {
        private static Singleton4 singleton = new Singleton4();
    }


    public static Singleton4 getObject() {
        return SingletonHolder.singleton;
    }
}
