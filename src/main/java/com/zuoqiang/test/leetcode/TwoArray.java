package com.zuoqiang.test.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 二维数组
 * @date 2021/5/31 4:15 下午
 */

@Slf4j
public class TwoArray {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        log.info("二维数组的宽度:{}", a[0].length);
        log.info("二维数组的高度:{}", a.length);
    }
}
