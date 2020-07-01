package com.zuoqiang.test.design.singleton;

/**
 * 但这是实现单例模式的最佳方法。它更简洁，自动支持序列化机制，绝对防止多次实例化。
 */
public enum EnumSingleObject {
    INSTANCE1();

    private Object instance;

}
