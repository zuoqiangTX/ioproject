package com.zuoqiang.test.design.singleton;

/**
 * 静态内部类
 */
public class StaticSingleObject {

    private StaticSingleObject() {

    }

    private static class SingletonHolder {
        private static final Object INSTANCE = new Object();
    }

    public static Object getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
