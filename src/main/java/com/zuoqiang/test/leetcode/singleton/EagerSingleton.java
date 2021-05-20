package com.zuoqiang.test.leetcode.singleton;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 饿汉
 * 单例模式，是指在任何时候，该类只能被实例化一次，在任何时候，访问该类的对象，对象都是同一的，只有一个。
 * @date 2021/5/20 10:30 上午
 */

public class EagerSingleton {
    private static EagerSingleton eagerSingleton = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return eagerSingleton;
    }
}
