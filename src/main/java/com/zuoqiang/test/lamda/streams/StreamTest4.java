package com.zuoqiang.test.lamda.streams;

import com.google.common.collect.Lists;
import com.zuoqiang.test.lamda.data.Person;

import java.util.List;

/**
 * stream- 排序
 *
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-08 23:43
 */
public class StreamTest4 {
    public static void main(String[] args) {
        List<Person> employees = Lists.newArrayList();
        employees.add(new Person("ziu", 11));
        employees.add(new Person("nie", 19));
        employees.add(new Person("ss", 13));
        employees.add(new Person("as", 13));
        employees.add(new Person("li", 12));
//        sorted自然排序 comparable()
        List<String> list = Lists.newArrayList("ccc", "aaa", "bbb", "ddd", "eee");
        list.stream().sorted().forEach(System.out::println);
        System.out.println("------------------------------");
        //定制排序
        employees.stream().sorted((e1, e2) ->
        {
            if (e1.getAge() == e2.getAge()) {
                return e2.getName().compareTo(e1.getName());
            } else {
                return e2.getAge() - e1.getAge();
            }

        }).forEach(System.out::println);


    }
}
