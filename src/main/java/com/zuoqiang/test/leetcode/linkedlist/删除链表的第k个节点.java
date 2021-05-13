package com.zuoqiang.test.leetcode.linkedlist;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * <p>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * </p>
 * @date 2021/5/13 4:16 下午
 **/

public class 删除链表的第k个节点 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        //对first节点进行操作
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        //接下来对secod和first进行操作
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }


    public static void main(String[] args) {

    }
}


