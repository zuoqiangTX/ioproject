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

    private static void quickSort(int[] num, int left, int right) {
        //如果left等于right，即数组只有一个元素，直接返回
        if (left >= right) {
            return;
        }
        //设置最左边的元素为基准值
        int base = num[left];
        //数组中比key小的放在左边，比key大的放在右边，key值下标为i
        int i = left;
        int j = right;
        while (i < j) {
            //j向左移，直到遇到比key小的值
            while (num[j] >= base && i < j) {
                j--;
            }
            //i向右移，直到遇到比key大的值
            while (num[i] <= base && i < j) {
                i++;
            }
            //i和j指向的元素交换
            if (i < j) {
                int temp = num[i];
                num[i] = num[j];
                num[j] = temp;
            }
        }
        num[left] = num[i];
        num[i] = base;
        quickSort(num, left, i - 1);
        quickSort(num, i + 1, right);
    }
}
