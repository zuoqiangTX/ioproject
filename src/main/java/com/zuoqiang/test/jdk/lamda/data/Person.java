package com.zuoqiang.test.jdk.lamda.data;

import lombok.Data;

/**
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-04 17:05
 */
@Data
public class Person {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
//        System.out.println("Person类的有参构造函数执行了！");
    }

    public Person() {
//        System.out.println("Person类的无参构造函数执行了！");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
