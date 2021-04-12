package com.zuoqiang.test.leetcode.ali;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2021/4/12 11:09 上午
 */

public class Test4 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean hasCyle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        //快慢指针
        ListNode low = head;
        ListNode fast = head.next;
        while (low != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            low.next = low.next;
            fast.next = fast.next.next;
        }
        return true;
    }
}
