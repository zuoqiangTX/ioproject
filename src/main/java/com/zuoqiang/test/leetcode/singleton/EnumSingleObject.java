package com.zuoqiang.test.leetcode.singleton;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 枚举实现
 * @date 2021/5/20 10:36 上午
 */

public enum EnumSingleObject {
    INSTANCE;

    public void doSomething() {
        System.out.println("doSomething");
    }

    public static void main(String[] args) {
        EnumSingleObject.INSTANCE.doSomething();

    }
}
