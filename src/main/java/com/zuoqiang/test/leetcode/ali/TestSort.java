package com.zuoqiang.test.leetcode.ali;

import java.util.*;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 第三题
 * @date 2021/4/12 11:00 上午
 */

public class TestSort {
    private int num;
    private double money;
    private Date time;

    @Override
    public String toString() {
        return "TestSort{" +
                "num=" + num +
                ", money=" + money +
                ", time=" + time +
                '}';
    }

    public TestSort(int num, double money, Date time) {
        this.num = num;
        this.money = money;
        this.time = time;
    }

    public static void main(String[] args) {
        List<TestSort> list = new ArrayList();
        Random r = new Random(1);
        for (int i = 0; i < 100000; i++) {
            list.add(new TestSort(r.nextInt(100), r.nextDouble(), new Date(System.currentTimeMillis())));
        }
        Collections.sort(list, new Comparator<TestSort>() {
            @Override
            public int compare(TestSort o1, TestSort o2) {
                //比较实际是否相同
                if (o1.time.getTime() == o2.time.getTime()) {
                    //比较钱，大的在前面
                    if (o1.money == o2.money) {
                        if (o1.num == o2.num) {
                            return 0;
                        } else {
                            return o1.num - o2.num > 0 ? 1 : -1;
                        }

                    } else {
                        return o1.money - o2.money > 0 ? 1 : -1;
                    }

                } else {
//                    不同的话大的排前面
                    return o1.time.getTime() - o2.time.getTime() > 0 ? 1 : -1;
                }
            }
        });
        		System.out.println(list);

    }
}
