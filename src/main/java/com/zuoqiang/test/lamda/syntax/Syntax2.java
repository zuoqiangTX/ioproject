package com.zuoqiang.test.lamda.syntax;

import com.zuoqiang.test.lamda.NoReturnMutiParam;
import com.zuoqiang.test.lamda.NoReturnSingleParam;
import com.zuoqiang.test.lamda.SingleReturSingleParam;
import com.zuoqiang.test.lamda.SingleReturnMutiPrams;

/**
 * lamda表达式的精简
 *
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-04 16:26
 */
public class Syntax2 {
    public static void main(String[] args) {

        //语法精简
        //1、参数 由于在接口的抽象方法中已经定义了参数的类型和数量,
//        所以在lamda表达式中参数的类型可以省略 备注：每一个参数的类型都要省略
        NoReturnMutiParam lamda1 = (a, b) -> {
            System.out.println("helloworld!");
        };

        //2、参数小括号
//        如果参数列表中，参数的数量只有一个，此时小括号可以省略
        NoReturnSingleParam lamda2 = a -> {
            System.out.println(a);
        };

        //3、关于方法大括号
        //方法体中只有一条语句，{}可以省略
        NoReturnSingleParam lamda3 = a -> System.out.println(a);

        //4、如果方法体中唯一的一个语句是一个返回语句，则在省略掉大括号的同时
//        ，return也必须省略。
        SingleReturSingleParam lamda4 = a -> 10;

        SingleReturnMutiPrams lamda5 = (a, b) -> a + b;
    }
}
