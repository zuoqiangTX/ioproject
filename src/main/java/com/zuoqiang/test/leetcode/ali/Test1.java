package com.zuoqiang.test.leetcode.ali;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 两个指针
 * @date 2021/4/12 11:00 上午
 */

public class Test1 {
    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode resverse(ListNode head) {
            ListNode pre = null;
            ListNode cur = head;
            while (cur != null) {
                ListNode tmp = head.next;
                cur.next = pre;
                pre = cur;
                cur = tmp;

            }
            return pre;

        }
    }
}
