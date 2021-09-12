package com.leetcode.binarytree;

import java.util.*;

/**
 * 199. Binary Tree Right Side View
 * Medium
 *
 * 4808
 *
 * 262
 *
 * Add to List
 *
 * Share
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 * Example 2:
 *
 * Input: root = [1,null,3]
 * Output: [1,3]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class BinaryTreeRightSideView {

    public class TreeNode {
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

    public List<Integer> rightSideView(TreeNode root) {

        // Edge cases
        if (root == null) {
            return Collections.emptyList();
        } else if (root.left == null && root.right == null) {
            return Collections.singletonList(root.val);
        }

        // Add nodes to que for processing
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        // Response list -- add root
        List<Integer> resp = new ArrayList<>();


        while (!que.isEmpty()) {
            TreeNode currNode = null;
            int levelSize = que.size();
            for (int i = 0; i < levelSize; i++) {
                currNode = que.poll();

                // Add current node childs to queue
                if (currNode.left != null) que.add(currNode.left);
                if (currNode.right != null) que.add(currNode.right);

                if (i == (levelSize - 1)) {
                    resp.add(currNode.val);
                }
            }
        }

        return resp;
    }
}
