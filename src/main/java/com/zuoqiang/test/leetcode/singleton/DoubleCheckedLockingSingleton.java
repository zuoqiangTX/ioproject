package com.zuoqiang.test.leetcode.singleton;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 双检锁
 * @date 2021/5/20 10:31 上午
 */

public class DoubleCheckedLockingSingleton {
    private static volatile DoubleCheckedLockingSingleton singleton;

    private DoubleCheckedLockingSingleton() {
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        if (singleton == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (singleton == null) {
                    singleton = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return singleton;
    }
}
