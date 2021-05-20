package com.zuoqiang.test.leetcode.singleton;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 静态内部类
 * @date 2021/5/20 10:33 上午
 */

public class LazyInitHolderSingleton {
    private static LazyInitHolderSingleton singleton;

    private static class SingletonHold {
        private static LazyInitHolderSingleton singleton = new LazyInitHolderSingleton();
    }

    private LazyInitHolderSingleton() {
    }

    public static LazyInitHolderSingleton getSingleton() {
        return SingletonHold.singleton;
    }
}
