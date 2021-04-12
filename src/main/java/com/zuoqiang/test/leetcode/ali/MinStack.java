package com.zuoqiang.test.leetcode.ali;

import java.util.Stack;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 最小栈
 * @date 2021/4/12 11:49 上午
 */

public class MinStack {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public MinStack() {
        this.dataStack = new Stack<Integer>();
        this.minStack = new Stack<Integer>();
    }

    public void push(int x) {
        //入栈
        if (this.minStack.isEmpty()) {
            this.minStack.push(x);
        } else if (x <= this.min()) {
            this.minStack.push(x);
        }
        this.dataStack.push(x);
    }

    public int pop() {
        //出栈
        if (this.dataStack.isEmpty()) {
            throw new RuntimeException("stack is Empty");
        }
        int result = this.dataStack.pop();
        if (result == this.min()) {
            this.minStack.pop();
        }
        return result;
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }

}
