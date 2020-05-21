package com.zuoqiang.test.guava;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zuoqiang
 * @version 1.0
 * @description Sets
 * <p>
 * 差集：
 * difference()函数返回两个集合的差集，即返回的在第一个集合但不在第二个集合中的元素
 * Sets.difference(set1, set2);
 * <p>
 * 交集：
 * <p>
 * intersection()方法用于返回两个或更多集合中都包含的元素，即交集。
 * <p>
 * Sets.intersection(set1,set2);
 * </p>
 * @date 2020/5/21 11:38 上午
 */

public class SetsTest {
    public static void main(String[] args) {

        Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 45, 6);
        Set<Integer> set2 = Sets.newHashSet(45, 8, 9);

        //差集
        System.out.println(Sets.difference(set1, set2));
        //交集
        System.out.println(Sets.intersection(set1, set2));
        testStreamIsNul();


    }

    private static void testStreamIsNul() {
        List<String> s = Lists.newArrayList();
        System.out.println(CollectionUtils.isEmpty(s));
//        List<String> s = null; //会报空指针
        s.stream().map(e -> {
            return new Object();
        }).collect(Collectors.toList());
    }
}
