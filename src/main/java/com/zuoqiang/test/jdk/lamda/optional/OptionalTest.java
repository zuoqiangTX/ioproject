package com.zuoqiang.test.jdk.lamda.optional;

import com.zuoqiang.test.jdk.lamda.data.Person;

import java.util.Optional;

/**
 * @author zuoqiang
 * @version 1.0
 * @description Optional 测试类
 * @date 2020/3/18 11:09 上午
 */

public class OptionalTest {
    public static void main(String[] args) {
        Person p = null;
        System.out.println(getName(p));
        System.out.println(getNameByOptional(p));

    }

    /**
     * 普通的判断写法
     *
     * @param person
     * @return
     */
    public static String getName(Person person) {
        if (person == null) {
            return "zuoqiang";
        }
        return person.getName();
    }

    /**
     * 使用optional 可以让代码更简洁
     *
     * @param person
     * @return
     */
    public static String getNameByOptional(Person person) {
        return Optional.ofNullable(person).map(u -> u.getName()).orElse("zuoqiang");
    }
}
