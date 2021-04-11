package com.zuoqiang.test.leetcode.mergeList;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 输入两个递增排序的链表
 * @date 2021/4/12 1:56 上午
 */

public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeList(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            l1.next = mergeList(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeList(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {

    }
}
