package com.zuoqiang.test.jdk.lamda.exercs;

import com.google.common.collect.Lists;
import com.zuoqiang.test.jdk.lamda.data.Person;

import java.util.List;

/**
 * 集合排序 lamda
 *
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-06 22:28
 */
public class exercs1 {
    public static void main(String[] args) {

//        将person对象按照年龄排序
        List<Person> list = Lists.newArrayList();
        list.add(new Person("ziu", 11));
        list.add(new Person("nie", 19));
        list.add(new Person("ss", 13));
        list.add(new Person("li", 12));


        list.sort((o1, o2) -> o1.age - o2.age);
        System.out.println(list);
    }
}
