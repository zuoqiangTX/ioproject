package com.zuoqiang.test.jdk.lamda.streams;

import com.zuoqiang.test.other.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 测试lamada排序
 * @date 2021/10/25 3:53 下午
 */

public class SortTest {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, 1, "z"));
        studentList.add(new Student(2, 2, "u"));
        studentList.add(new Student(3, 3, "o"));
        //升序
        System.out.println(studentList.stream().sorted(Comparator.comparing(Student::getAge)).collect(Collectors.toList()));
        //降序
        System.out.println(studentList.stream().sorted(Comparator.comparing(Student::getAge).reversed()).collect(Collectors.toList()));
    }
}
