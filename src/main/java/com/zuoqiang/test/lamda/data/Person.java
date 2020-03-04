package com.zuoqiang.test.lamda.data;

/**
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-04 17:05
 */
public class Person {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Person类的有参构造函数执行了！");
    }

    public Person() {
        System.out.println("Person类的无参构造函数执行了！");
    }
}
