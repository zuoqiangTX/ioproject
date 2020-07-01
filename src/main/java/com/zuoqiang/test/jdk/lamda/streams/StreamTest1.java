package com.zuoqiang.test.jdk.lamda.streams;

import com.zuoqiang.test.jdk.lamda.data.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 流式api的操作步骤
 * 1、创建流 2、中间操作 3、中断操作
 *
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-08 23:43
 */
public class StreamTest1 {
    public static void main(String[] args) {
//        1、创建stream
//        1) Collection集合提供的stream方法.parallelStream
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
//          2）Arrays静态方法streams
        Person[] people = new Person[10];
        Stream<Person> stream1 = Arrays.stream(people);
        //  3)stream中的静态方法
        Stream<Person> people1 = Stream.of(people);
        //  4）创建无限流
        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 2);
        stream2.limit(10).forEach(System.out::println);

        Stream.generate(() -> Math.random()).limit(5)
                .forEach(System.out::println);


    }
}
