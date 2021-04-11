package com.zuoqiang.test.leetcode.tree.pre;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zuoqiang
 * @version 1.0
 * @description 二叉树的前序遍历
 * <p>
 * 首先我们需要了解什么是二叉树的前序遍历：按照访问根节点——左子树——右子树的方式遍历这棵树，而在访问左子树或者右子树的时候，我们按照同样的方式遍历，直到遍历完整棵树。因此整个遍历过程天然具有递归的性质，我们可以直接用递归函数来模拟这一过程。
 * <p>
 * 定义 preorder(root) 表示当前遍历到 root 节点的答案。按照定义，我们只要首先将 root 节点的值加入答案，然后递归调用 preorder(root.left) 来遍历 root 节点的左子树，最后递归调用 preorder(root.right) 来遍历 root 节点的右子树即可，递归终止的条件为碰到空节点。
 * <p>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/er-cha-shu-de-qian-xu-bian-li-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @date 2021/4/12 1:56 上午
 */

public class PreTree {

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
        //存放res
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    /**
     * 前序遍历非递归
     *
     * @param root
     * @param res
     */
    private void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        //左子树
        preorder(root.left, res);
        //右子树
        preorder(root.right, res);
    }
}
