package com.zuoqiang.test.jdk.compator;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;


/**
 * 两种方法各有优劣, 用Comparable 简单, 只要实现Comparable 接口的对象直接就成为一个可以比较的对象,
 * 但是需要修改源代码, 用Comparator 的好处是不需要修改源代码, 而是另外实现一个比较器, 当某个自定义
 * 的对象需要作比较的时候,把比较器和对象一起传递过去就可以比大小了, 并且在Comparator 里面用户可以自
 * 己实现复杂的可以通用的逻辑,使其可以匹配一些比较简单的对象,那样就可以节省很多重复劳动了。
 * <p>
 * compareTo就是比较两个值，如果前者大于后者，返回1，等于返回0，小于返回-1，我下面给出了例子，由于比较的变量我用的是int，int型可以直接比较，所有没有用到compareTo比较，如果声明的是Date、String、Integer或者其他的，可以直接使用compareTo比较，
 * ————————————————
 * 版权声明：本文为CSDN博主「随机漫步_」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/asdfsadfasdfsa/article/details/52795438
 *
 * @author tongbanjie
 */
public class CompatorPerson implements Comparable<CompatorPerson> {

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

    public CompatorPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "CompatorPerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    /**
     * 如果该对象小于、等于或大于指定对象，则分别返回负整数、零或正整数。
     */
    public int compareTo(CompatorPerson another) {
        int i = 0;
        i = this.getName().compareTo(another.getName()); // 使用字符串的比较
        if (i == 0) { // 如果名字一样,比较年龄, 返回比较年龄结果
            return this.getAge() - another.getAge();
        } else {
            return i; // 名字不一样, 返回比较名字的结果.
        }
    }

    public static void main(String[] args) {
        List<CompatorPerson> list = Lists.newArrayList();
        list.add(new CompatorPerson("1号", 1));
        list.add(new CompatorPerson("1号", 2));
        list.add(new CompatorPerson("3号", 3));
        Collections.sort(list);
        System.out.println(list);
    }
}
