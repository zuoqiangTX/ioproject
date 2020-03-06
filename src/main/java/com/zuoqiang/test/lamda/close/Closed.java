package com.zuoqiang.test.lamda.close;

import java.util.function.Supplier;

/**
 * 闭包
 *
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-06 23:33
 */
public class Closed {
    public static void main(String[] args) {
        int n = getNumer().get();
        System.out.println(n);

    }

    private static Supplier<Integer> getNumer() {
        int num = 10;
        return () -> num;
    }

}
