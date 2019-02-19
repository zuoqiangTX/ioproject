package com.zuoqiang.test.other;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author baiyue
 */
public class Student implements Comparable<Student> {
    private int id;
    private int age;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

    /**
     * 返回值	含义
     * 负整数	    当前对象的值 < 比较对象的值 ， 位置排在前
     * 零	    当前对象的值 = 比较对象的值 ， 位置不变
     * 正整数	    当前对象的值 > 比较对象的值 ， 位置排在后
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Student o) {
        //降序
        //return o.age - this.age;
        //升序，越大的当前对象越在后面
        return this.age - o.age;
    }

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, 25, "关羽"));
        list.add(new Student(2, 21, "张飞"));
        list.add(new Student(3, 18, "刘备"));
        list.add(new Student(4, 32, "袁绍"));
        list.add(new Student(5, 36, "赵云"));
        list.add(new Student(6, 16, "曹操"));
        System.out.println("排序前:");
        for (Student student : list) {
            System.out.println(student.toString());
        }
//        //默认排序
//        defaultSort(list);

        //当需求是根据ID排序的时候，需要重新写排序器
        System.out.println("自定义排序后:");
        //写法1
        customerSort1(list);
        //写法2
//        customerSort2(list);

    }

    /**
     * 自定义排序2
     *
     * @param list
     */
    private static void customerSort2(List<Student> list) {
        list.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.id - o2.id;
            }
        });
        for (Student student : list) {
            System.out.println(student.toString());
        }
    }

    /**
     * 自定义排序1
     *
     * @param list
     */
    private static void customerSort1(List<Student> list) {
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                //o1的id越大位置越后，故是升序
                return o1.id - o2.id;
            }
        });
        for (Student student : list) {
            System.out.println(student.toString());
        }
    }

    /**
     * 默认排序
     *
     * @param list
     */
    private static void defaultSort(List<Student> list) {
        System.out.println("默认排序后:");
        Collections.sort(list);
        for (Student student : list) {
            System.out.println(student.toString());
        }
    }
}
