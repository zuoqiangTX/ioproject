package com.zuoqiang.test.leetcode.fbi;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 斐波那契数列
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * @date 2021/4/12 1:18 上午
 */

public class FbiTest {
    public int fbi(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fbi(n - 1) + fbi(n - 2);
    }

    public static void main(String[] args) {
        FbiTest fbiTest = new FbiTest();
        System.out.println(fbiTest.fbi(2));
        System.out.println(fbiTest.fbi(10));
    }
}
