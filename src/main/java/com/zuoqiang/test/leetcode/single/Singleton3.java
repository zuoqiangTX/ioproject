package com.zuoqiang.test.leetcode.single;

/**
 * @author zuoqiang
 * @version 1.0
 * @description Singleton
 * @date 2021/4/12 1:11 上午
 */

public class Singleton3 {
    private volatile Singleton3 singleton;

    private Singleton3() {

    }

    public Singleton3 getObject() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton3();
                }
            }
        }
        return singleton;
    }
}
