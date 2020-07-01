package com.zuoqiang.test.jdk.lamda.defaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 方法引用测试
 * @date 2020/3/23 3:29 下午
 */

public class MethodTest {
    public static void main(String[] args) {
        MethodTest test = new MethodTest();
//        totalTest();
//        testStaticMethod();
//        testConstrct();
//        testObjectMethod();
        testNoStatic(test);
    }

    private static void testNoStatic(MethodTest test) {
        //lamda表达式
        Arrays.asList(new String[]{"a", "c", "b"}).stream().forEach(e -> test.testPrintNostatic(e));
        //静态方法引用
        Arrays.asList(new String[]{"a", "c", "b"}).stream().forEach(test::testPrintNostatic);
    }

    private static void testObjectMethod() {
        String[] strs = {"a", "c", "b"};
        Arrays.sort(strs, (s1, s2) -> s1.compareToIgnoreCase(s2));
        Arrays.sort(strs, String::compareToIgnoreCase);
        Arrays.stream(strs).forEach(e -> System.out.println(e));
    }

    private static void testConstrct() {
        //测试
        Supplier<List<String>> supplier1 = () -> new ArrayList<String>();
        Supplier<List<String>> supplier = ArrayList<String>::new;
    }

    /**
     * 测试静态方法引用
     * 静态方法引用适用于lambda表达式主体中仅仅调用了某个类的静态方法的情形。
     * 静态方法引用的语法格式为： 类名::静态方法名
     */
    private static void testStaticMethod() {
        //lamda表达式
        Arrays.asList(new String[]{"a", "c", "b"}).stream().forEach(e -> MethodTest.testPrint(e));
        //静态方法引用
        Arrays.asList(new String[]{"a", "c", "b"}).stream().forEach(MethodTest::testPrint);
    }

    private static void totalTest() {
        List<String> strList = Arrays.asList(new String[]{"a", "c", "b"});

        strList.stream().sorted((s1, s2) -> s1.compareToIgnoreCase(s2)).forEach(s -> System.out.println(s));
        System.out.println(">>>>>>>>>>>>类的任意对象的实例方法引用和特定对象的实例方法>>>>>>>>>>>>");
        strList.stream().sorted(String::compareToIgnoreCase).forEach(System.out::println);
    }

    public static void testPrint(String s) {
        System.out.println(s);
    }

    public void testPrintNostatic(String s) {
        System.out.println(s);
    }
}
