package com.zuoqiang.test.other.object;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class A {
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

    public String toString() {
        System.out.println(ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE));
        return "i am " + name + ", i " + age;

    }

    public A() {
    }

    public A(String name, int age) {
        this.name = name;
        this.age = age;
    }


}
