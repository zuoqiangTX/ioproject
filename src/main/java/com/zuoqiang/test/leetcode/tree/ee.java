package com.zuoqiang.test.leetcode.tree;

import java.util.Arrays;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2021/4/12 2:16 上午
 */

public class ee {
    public static void main(String[] args) {
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    private static void quickSort(int[] a, int low, int high) {
        if (low > high) {
            return;
        }
        int base = a[low];
        int i = low;
        int j = high;
        while (i < j) {
            while (a[j] >= base && i < j) {
                j--;
            }
            while (a[i] < base && i < j) {
                i++;
            }
            if (i < j) {
                int temp = a[j];
                a[j] = a[i];
                a[i] = temp;
            }
        }
//        将基准数放到中间的位置（基准数归位）
        a[low] = a[i];
        a[i] = base;
        quickSort(a, low, i - 1);
        quickSort(a, i + 1, high);
    }
}
