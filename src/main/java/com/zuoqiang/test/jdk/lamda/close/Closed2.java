package com.zuoqiang.test.jdk.lamda.close;

import java.util.function.Consumer;

/**
 * 闭包中引用的变量一定是常量，系统会自动加上final关键字。
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-06 23:36
 */
public class Closed2 {
    public static void main(String[] args) {
        int a = 10;
        Consumer<Integer> c = ele -> {
            System.out.println(a);
//            System.out.println(a++); 错误情况，会自动加上final
        };
//        a++;  错误情况，会自动加上final

        c.accept(a);
    }
}
