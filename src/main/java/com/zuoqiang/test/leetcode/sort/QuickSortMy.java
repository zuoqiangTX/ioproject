package com.zuoqiang.test.leetcode.sort;

import java.util.Arrays;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 快速排序
 * @date 2021/4/12 3:53 下午
 */

public class QuickSortMy {
    public static void main(String[] args) {
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int base = arr[left];
        int i = left;
        int j = right;
        while (i != j) {
            //先从右边
            while (arr[j] >= base && i < j) {
                j--;
            }
            while (arr[j] <= base && i < j) {
                i++;
            }
            //上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if (i < j) {
                //交互位置
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }

        //交互第一个位置和中间位置
        arr[left] = arr[i];
        arr[i] = base;
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }
}
