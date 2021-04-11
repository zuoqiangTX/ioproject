package com.zuoqiang.test.leetcode.tree.pre;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2021/4/12 2:17 上午
 */

public class Tree {

    public static class TreeNode {
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

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        //用一个stack
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                //入栈
                node = node.left;
            }
            node = stack.pop();
            //
            node = node.right;
        }
        return res;
    }

    public static void main(String[] args) {
        Tree treeNode = new Tree();
        System.out.println(treeNode.preorderTraversal(new TreeNode(1, null, new TreeNode(2, new TreeNode(3, null, null), null))));
    }
}
