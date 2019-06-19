package com.zuoqiang.test.singleton;

/**
 * 懒汉模式 需要的时候再创建 线程不安全
 *
 * @author baiyue
 */
public class lHSingleObject {
    private static Object instance = null;

    private lHSingleObject() {

    }

    public static synchronized Object getInstance() {
        if (instance == null) {
            instance = new Object();
        }
        return instance;
    }

//    public static  Object getInstance() {
//        if (instance == null) {
//            instance = new Object();
//        }
//        return instance;
//    }

}
