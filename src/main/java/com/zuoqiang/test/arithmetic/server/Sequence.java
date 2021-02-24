package com.zuoqiang.test.arithmetic.server;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2021/2/24 4:03 下午
 */

public class Sequence {
    public static Integer num = 0;

    public static Integer getAndIncrement() {
        return ++num;
    }
}
