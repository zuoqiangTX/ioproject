package com.zuoqiang.test.jdk.lamda.syntax;

import com.zuoqiang.test.jdk.lamda.data.Person;

/**
 * 构造函数的方法引用
 *
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-04 17:06
 */
public class Syntax4 {
    public static void main(String[] args) {
        PersonCreate create = () -> new Person();

        //构造方法的引用:
        PersonCreate create1 = Person::new;
        Person a = create1.getPerson();

        PersonCreate2 create2 = Person::new;
        Person b = create2.getPerson("s", 10);
    }

}

//需求:

interface PersonCreate {
    Person getPerson();
}

interface PersonCreate2 {
    Person getPerson(String name, int age);
}