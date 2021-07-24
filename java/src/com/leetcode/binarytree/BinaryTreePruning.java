package com.leetcode.binarytree;

public class BinaryTreePruning {
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

    public TreeNode pruneTree(TreeNode root) {
        boolean returnVal = isPrune(root);
        if (returnVal) {
            return null;
        }
        return root;
    }

    public boolean isPrune(TreeNode node) {

        // check left
        boolean isLeft = true;
        if (node.left != null) {
            isLeft = isPrune(node.left);
            if (isLeft) {
                node.left = null;
            }
        }

        // Check right
        boolean isRight = true;
        if (node.right != null) {
            isRight = isPrune(node.right);
            if (isRight) {
                node.right = null;
            }
        }

        // if left and right both doesnt have 1 and val also doesnt have 1 then return prune (true)
        if (node.val == 1 || !isLeft || !isRight) {
            return false;
        }
        return true;

    }
}
