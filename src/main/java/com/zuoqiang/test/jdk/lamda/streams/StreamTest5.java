package com.zuoqiang.test.jdk.lamda.streams;

import com.google.common.collect.Lists;
import com.zuoqiang.test.jdk.lamda.data.Person;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zuoqiang
 * @version 1.0
 * @description stream流式集合元素 collect
 * @date 2020/5/7 3:54 下午
 */

public class StreamTest5 {
    public static void main(String[] args) {
        List<Person> employees = Lists.newArrayList();
        //如果为空值就会报错，所以stream之前要判空
//        employees = null;
//        employees.stream().forEach(System.out::println);
        employees.add(new Person("ziu", 11));
        employees.add(new Person("nie", 19));
        employees.add(new Person("ss", 13));
        employees.add(new Person("as", 13));
        employees.add(new Person("li", 12));
        String mergeKey = employees.stream().map(Person::getName).collect(Collectors.joining("+"));
        String mergeKey1 = employees.parallelStream().map(Person::getName).collect(Collectors.joining("+"));
        System.out.println(mergeKey);
        System.out.println(mergeKey1);
        String key1 = employees.stream().map(Person::getName).collect(Collectors.joining());
        System.out.println(key1);
        List<String> collect = employees.stream().map(Person::getName).collect(Collectors.toList());
        List<String> collect1 = employees.parallelStream().map(Person::getName).collect(Collectors.toList());
        System.out.println("集合为list" + collect);
        System.out.println("集合为list" + collect1);
        List<Person> list = employees.stream().map(employee -> {
            Person person = new Person();
            person.setName("0");
            person.setAge(100);
            return person;
        }).collect(Collectors.toList());
        System.out.println(list);
    }
}
