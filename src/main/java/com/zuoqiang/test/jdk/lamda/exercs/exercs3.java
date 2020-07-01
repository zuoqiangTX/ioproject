package com.zuoqiang.test.jdk.lamda.exercs;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * 集合遍历 foreach
 *
 * @author zuoqiang
 * @version 1.0
 * @date 2020-03-06 22:28
 */
public class exercs3 {
    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();
        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        //将集合的每一个元素都带入到方法accept中
//        list.forEach(System.out::println);

//输出集合中所有的偶数
        list.forEach(ele -> {
            if (ele % 2 == 0) {
                System.out.println(ele);
            }
        });
    }
}
