package com.zuoqiang.test.jdk.lamda.syntax;

import com.zuoqiang.test.jdk.lamda.*;

/**
 * lamda基础语法
 * ()  描述参数列表   {}描述方法体  -> lamda运算符（goes to）
 *
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-04 15:33
 */
public class Syntax1 {
    public static void main(String[] args) {
        //1、lamda表达式的基础语法
        //lamda是一个匿名函数
        // 参数列表 方法体
        // ()  描述参数列表   {}描述方法体  -> lamda运算符（goes to）

        //无参无返回
        NoReturnNoParam lamda1 = () -> {
            System.out.println("HelloWorld!");
        };
        lamda1.test();

        //单参无返回
        NoReturnSingleParam lamda2 = (int a) -> {
            System.out.println(a);
        };
        lamda2.test(10);

        //多参无返回
        NoReturnMutiParam lamda3 = (int a, int b) -> {
            System.out.println(a + b);
        };
        lamda3.test(10, 11);


        //无参单返回
        SingleReturnNoParam lamda4 = () -> {
            System.out.println("lmada4");
            return 100;
        };
        System.out.println(lamda4.test());

        //单参单返回
        SingleReturSingleParam lamda5 = (int a) -> {
            return a * 2;
        };
        System.out.println(lamda5.test(10));

        //多参有返回值
        SingleReturnMutiPrams lamda6 = (int a, int b) -> {
            return a * b;
        };
        System.out.println(lamda6.test(20, 30));
    }
}
