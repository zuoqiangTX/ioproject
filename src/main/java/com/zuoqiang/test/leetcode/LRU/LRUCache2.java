package com.zuoqiang.test.leetcode.LRU;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 链表实现
 * @date 2021/4/12 12:06 上午
 */

public class LRUCache2 {
    private int capacity;
    private Map<Integer, Node> map;
    private DoubleList doubleList;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.doubleList = new DoubleList();
    }

    public int get(int key) {
        //先获取看有没有,没有的话返回-1
        if (map.containsKey(key)) {
            //返回节点，将其放在第一位置
            Node node = map.get(key);
            //双向链表移动到头部
            doubleList.moveToFirst(node);
            return node.val;
        }
        return -1;

    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if (map.containsKey(key)) {
            doubleList.remove(map.get(key));
            //放在第一位
            doubleList.addFirst(node);
            map.put(key, node);
        } else {
            if (doubleList.size() == capacity) {
                //删除最后一个
                map.remove(doubleList.removeLast().key);
            }
            map.put(key, node);
            //添加到第一位
            doubleList.addFirst(node);
        }

    }


    class DoubleList {
        //头
        Node head;
        //尾巴
        Node tail;

        //数量
        int count;

        public DoubleList() {
            head = new Node(null, null);
            tail = new Node(null, null);
            head.next = tail;
            head.pre = null;
            tail.pre = head;
            tail.next = null;
            count = 0;
        }

        //添加到表头
        public void addFirst(Node node) {
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
            count++;

        }

        //删除
        public void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            count--;
        }

        //删除到表尾部
        public Node removeLast() {
            Node node = tail.pre;
            tail.pre = node.pre;
            tail.pre.next = tail;
            count--;
            return node;
        }

        public int size() {
            return count;
        }

        /**
         * 移动到头部
         */
        public void moveToFirst(Node node) {
            remove(node);
            addFirst(node);
        }
    }

    /**
     * 双向链表
     */
    class Node {
        Node pre;
        Node next;
        private Integer key;
        private Integer val;

        public Node(Integer key, Integer val) {
            this.key = key;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        LRUCache2 lRUCache = new LRUCache2(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(3));
        System.out.println(lRUCache.get(4));
    }
}
