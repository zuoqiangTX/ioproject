package com.zuoqiang.test.singleton;

/**
 * 用双锁机制，安全且在多线程情况下能保持高性能
 */
public class SJSingleObject {

    private static volatile Object instance;

    private SJSingleObject() {

    }

    public static Object getInstance() {
        if (instance == null) {
            synchronized (SJSingleObject.class) {
                if (instance == null) {
                    instance = new Object();

                }
            }
        }
        return instance;
    }

}
