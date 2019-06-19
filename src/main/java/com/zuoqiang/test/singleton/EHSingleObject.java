package com.zuoqiang.test.singleton;

/**
 * 饿汉模式
 * <p>
 * 它的私有构造函数和本身的一个静态实例
 * </p>
 *
 * @author baiyue
 */
public class EHSingleObject {
    private static Object instance = new Object();

    private EHSingleObject() {

    }

    public static Object getInstance() {
        return instance;
    }
}
