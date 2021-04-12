package com.zuoqiang.test.leetcode.linkedlist;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 使用双指针
 * @date 2021/4/12 9:57 上午
 */

public class 链表逆序 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //迭代法
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            //向前指针
            cur.next = pre;
            //pre++
            pre = cur;
            //cur++
            cur = tmp;
        }
        return pre;
    }
}
