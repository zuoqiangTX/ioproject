package com.zuoqiang.test.Object;

public class TestA {


    public static void main(String args[]) {

        A a = new A("tom", 11);
        //A a = new A();
//        A a = null;
        System.out.println("最初的属性" + a);
        test2(a);
        System.out.println("函数内指向新的引用" + a + ",没有起作用");
        test1(a);
        System.out.println("函数内使用set赋值" + a + ",改变了");

    }

    public static void test1(A a) {
        a.setAge(12);
        a.setName("jack");
    }

    public static void test2(A a) {
        a = new A("jack", 12);
    }

}
