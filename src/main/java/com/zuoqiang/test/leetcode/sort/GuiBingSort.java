package com.zuoqiang.test.leetcode.sort;

import java.util.Arrays;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 归并排序
 * @date 2021/7/31 9:23 下午
 */

public class GuiBingSort {

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        mySort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void mySort(int[] arr, int left, int right) {
        int mid = left + (right - left) / 2;
        if (left < right) {
            mySort(arr, left, mid);
            mySort(arr, mid + 1, right);
            sortMerge(arr, left, mid, right);
        }
    }

    private static void sortMerge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int index = 0;
        int p1 = low;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= high) {
            if (arr[p1] <= arr[p2]) {
                temp[index++] = arr[p1++];
            } else {
                temp[index++] = arr[p2++];
            }
        }
        while (p1 <= mid) {
            temp[index++] = arr[p1++];
        }
        while (p2 <= high) {
            temp[index++] = arr[p2++];
        }
        for (int i = 0; i < temp.length; i++) {
            arr[low++] = temp[i];
        }
    }

}
