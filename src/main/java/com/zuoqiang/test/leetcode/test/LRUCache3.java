package com.zuoqiang.test.leetcode.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 自己做的lru
 * @date 2021/4/12 12:51 上午
 */

class LRUCache {
    private int capacity;
    private Map<Integer, Node> map;
    private DoubleList doubleList;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap(capacity);
        this.doubleList = new DoubleList();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            //将链表移动到头
            doubleList.moveToFirst(node);
            return node.val;

        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if (map.containsKey(key)) {
            doubleList.remove(node);
            doubleList.addFirst(node);
            map.put(key, node);
        } else {
            //不存在
            if (doubleList.size() == capacity) {
                map.remove(doubleList.removeLast().key);
            }
            map.put(key, node);
            doubleList.addFirst(node);
        }
    }

    class DoubleList {
        int count = 0;
        Node head;
        Node tail;

        public DoubleList() {
            head = new Node(null, null);
            tail = new Node(null, null);
            head.pre = null;
            head.next = tail;
            tail.pre = head;
            tail.next = null;
        }

        public void addFirst(Node node) {
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
            count++;

        }

        public int size() {
            return count;
        }

        public Node removeLast() {
            Node node = tail.pre;
            tail.pre = node.pre;
            node.pre.next = tail;
            count--;
            return node;
        }

        public void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            count--;
        }

        public void moveToFirst(Node node) {
            remove(node);
            addFirst(node);
        }
    }

    class Node {
        Node pre;
        Node next;
        Integer key;
        Integer val;

        public Node(Integer key, Integer val) {
            this.key = key;
            this.val = val;
        }
    }


    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
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
