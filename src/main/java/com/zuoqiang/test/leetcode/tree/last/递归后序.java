package com.zuoqiang.test.leetcode.tree.last;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zuoqiang
 * @version 1.0
 * @description TreeNode 根节点——左子树——右子树
 * @date 2021/4/13 2:35 下午
 */

public class 递归后序 {
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

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        return result;
    }

    private void dfs(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        dfs(root.left, result);
        dfs(root.right, result);
        result.add(root.val);
    }
}
