package com.zuoqiang.test.leetcode.tree.last;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 后序遍历
 * while( 栈非空 || p 非空)
 * {
 * if( p 非空)
 * {
 * <p>
 * }
 * else
 * {
 * <p>
 * }
 * }
 * @date 2021/4/12 3:07 上午
 */

public class 迭代后序 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.peek();
            if (node.right == null || node.right == root) {
                res.add(node.val);
                root = node;
                stack.pop();
                node = null;
            } else {
                node = node.right;
            }

        }
        return res;
    }

}
