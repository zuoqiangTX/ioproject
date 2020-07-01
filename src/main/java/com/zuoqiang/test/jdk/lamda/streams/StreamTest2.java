package com.zuoqiang.test.jdk.lamda.streams;

import com.google.common.collect.Lists;
import com.zuoqiang.test.jdk.lamda.data.Person;

import java.util.List;
import java.util.stream.Stream;

/**
 * stream中间操作-筛选与切片
 *
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-08 23:43
 */
public class StreamTest2 {
    private static List<Person> list;

    static {
        list = Lists.newArrayList();
        list.add(new Person("ziu", 11));
        list.add(new Person("nie", 19));
        list.add(new Person("ss", 13));
        list.add(new Person("ss", 13));
        list.add(new Person("li", 12));
    }

    public static void main(String[] args) {
//        testFilter();
//        testLimit();
//        testSkip();
        testDistinct();

    }

    private static void testFilter() {
        //        中间操作，不会进行任何操作
//        内部迭代：迭代操作由stream API完成
        Stream<Person> stream = StreamTest2.list.stream().filter((e) -> {
            System.out.println("中间操作");
            return e.age > 12;
        });
//        终止操作，一次性执行全部内容，即进行惰性求值；
        stream.forEach(System.out::println);

        //外部迭代：iterator
    }

    private static void testLimit() {
        StreamTest2.list.stream().
                filter((e) -> e.age > 11).limit(1).forEach(System.out::println);
    }


    private static void testSkip() {
        StreamTest2.list.stream().
                filter((e) -> e.age > 11).skip(1).forEach(System.out::println);
    }

    private static void testDistinct() {
        //流的元素根据equals和hashcode来判断是否相等进行去重
        StreamTest2.list.stream().
                filter((e) -> e.age > 11).skip(1).distinct().
                forEach(System.out::println);
    }
}
