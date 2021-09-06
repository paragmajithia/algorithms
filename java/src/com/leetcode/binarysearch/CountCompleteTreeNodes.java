package com.leetcode.binarysearch;

/**
 * 222. Count Complete Tree Nodes
 * Medium
 *
 * 3427
 *
 * 274
 *
 * Add to List
 *
 * Share
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 *
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Design an algorithm that runs in less than O(n) time complexity.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: 6
 * Example 2:
 *
 * Input: root = []
 * Output: 0
 * Example 3:
 *
 * Input: root = [1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5 * 104].
 * 0 <= Node.val <= 5 * 104
 * The tree is guaranteed to be complete.
 */




public class CountCompleteTreeNodes {
    public static void main(String[] args) {
        CountCompleteTreeNodes main = new CountCompleteTreeNodes();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        System.out.println("Count of complete binary tree: " + main.countNodes(root));
    }
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int countNodes(TreeNode root) {

        // Get height of current and right sub tree
        int hieght = height(root);

        if (hieght < 0) {
            return 0;
        }

        int rightSubTreHeight = height(root.right);

        // Case 1 -- left subtree and right subtree has same height
        // Return count of nodes of left subtree + 1 for root + get count of right subtree
        // nodes of left subtree is 2^h -1
        if (hieght == (rightSubTreHeight + 1)) {
            return ((1 << hieght) + countNodes(root.right));
        }
        // Case 2 -- left subtree and right subtree has different height. Right subtree has h-2 height (instead of h-1)
        // Return count of nodes of right subtree + 1 for root + get count of left subtree
        // nodes of right subtree is 2^(h-1) -1
        else {
            return ((1 << (hieght-1)) + countNodes(root.left));
        }

    }

    public int height(TreeNode root) {

        if (root == null) {
            return -1;
        }
        return (1 + height(root.left));

    }
}