package com.zuoqiang.test.leetcode.sort;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author zuoqiang
 * @version 1.0
 * @description collections sort
 * @date 2021/4/14 3:25 下午
 */

public class CollectionsTest {
    public static void main(String[] args) {
//        Collections.sort测试
        Integer a = 5;
        Integer b = 6;
//        其实等于a-b;
        System.out.println("比较大小" + a.compareTo(b));
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        // 返回值为int类型，大于0表示正序，小于0表示逆序
        list.sort((o1, o2) -> o2 - o1);
        System.out.println(list);


    }
}
