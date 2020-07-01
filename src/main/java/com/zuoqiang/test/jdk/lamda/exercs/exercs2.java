package com.zuoqiang.test.jdk.lamda.exercs;

import com.zuoqiang.test.jdk.lamda.data.Person;

import java.util.TreeSet;

/**
 * 集合排序 Treeset lamda
 *
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-06 22:28
 */
public class exercs2 {
    public static void main(String[] args) {

//        将person对象按照年龄排序使用lamda表达式实现compartor接口
//        TreeSet<Person> set = new TreeSet<>((o1, o2) -> o1.age - o2.age);
        TreeSet<Person> set = new TreeSet<>((o1, o2) -> {
            if (o1.age >= o2.age) {
                return -1;
            } else {
                return 1;
            }
        });
        set.add(new Person("ziu", 11));
        set.add(new Person("nie", 19));
        set.add(new Person("ss", 13));
        set.add(new Person("ss", 13));
        set.add(new Person("li", 12));


        System.out.println(set);
    }
}
