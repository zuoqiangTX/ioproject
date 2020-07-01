package com.zuoqiang.test.jdk.lamda.streams;

import com.google.common.collect.Lists;
import com.zuoqiang.test.jdk.lamda.data.Person;

import java.util.List;

/**
 * stream- 映射
 * map && flapMap
 *
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-08 23:43
 */
public class StreamTest3 {
    public static void main(String[] args) {
        //map 得到一个新的流，针对每一个元素进行操作，一个流中可能还嵌套着多层流
        List<String> list1 = Lists.newArrayList("aa", "bb", "cc", "dd", "ee");
        list1.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

        List<Person> list = Lists.newArrayList();
        list.add(new Person("ziu", 11));
        list.add(new Person("nie", 19));
        list.add(new Person("ss", 13));
        list.add(new Person("ss", 13));
        list.add(new Person("li", 12));
        list.stream().map(Person::getName).forEach(System.out::println);

        //flatMap 接受一个函数，将流中的每个值转换为流，然后在连接起来，变成一个流

    }
}
