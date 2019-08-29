package com.zuoqiang.test.other.compator;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Person implements Serializable {

    private static final long serialVersionUID = 4150207079139375648L;

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        List<Person> list = Lists.newArrayList();
        list.add(new Person("1号", 1));
        list.add(new Person("1号", 2));
        list.add(new Person("3号", 3));
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                int i = 0;
                i = o1.getName().compareTo(o2.name); // 使用字符串的比较
                if(i == 0) { // 如果名字一样,比较年龄,返回比较年龄结果
                    return o1.getAge() - o1.getAge();
                } else {
                    return i; // 名字不一样, 返回比较名字的结果.
                }
            }
        });
        System.out.println(list);
    }
}
