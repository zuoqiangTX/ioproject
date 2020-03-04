package com.zuoqiang.test.lamda.syntax;

import com.zuoqiang.test.lamda.SingleReturSingleParam;

/**
 * lamda进阶之-方法引用 基础使用
 *
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-04 16:57
 */
public class Syntax3 {
    public static void main(String[] args) {

//        方法引用
//        可以快速把一个lamda表达式的实例指向一个已经实现的方法;
//        语法：方法的隶属者::方法名

//        注意：
//        1.参数类型和数量也和接口中定义方法一致
        // 2.返回值的类型也和接口中定义方法一致
        SingleReturSingleParam lamda1 = a -> a * 2;
        SingleReturSingleParam lamda2 = a -> a * 2;
        SingleReturSingleParam lamda3 = a -> change(a);
        //方法引用：引用了change
        SingleReturSingleParam lamda4 = Syntax3::change;


    }

    private static int change(int a) {
        return a * 2;
    }
}
