package com.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Definition for a binary tree node.
 *107. Binary Tree Level Order Traversal II
 * Medium
 *
 * 3160
 *
 * 278
 *
 * Add to List
 *
 * Share
 * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[15,7],[9,20],[3]]
 * Example 2:
 *
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 * Accepted
 * 496,580
 * Submissions
 * 846,181
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * */

class BinaryTreeLevelOrderTraversal2 {

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

    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> response = new ArrayList<>();
        List<TreeNode> currentLevel = new ArrayList<>();
        currentLevel.add(root);
        recurse(currentLevel, response);

        return response;
    }

    public void recurse(List<TreeNode> currentLevel, List<List<Integer>> response) {

        List<TreeNode> nextLevel = new ArrayList<>();

        // Loop through all child nodes
        for (TreeNode node: currentLevel) {
            if (node.left != null) nextLevel.add(node.left);
            if (node.right != null) nextLevel.add(node.right);
        }

        // Go down the level
        if (!nextLevel.isEmpty()) {
            recurse(nextLevel, response);
        }

        // Add the current level to response
        response.add(currentLevel.stream().map(node -> node.val).collect(Collectors.toList()));
    }
}

/**
 OTHER SOLUTION USING LINKEDLIST ADDFIRST

 public List<List<Integer>> levelOrderBottom(TreeNode root) {
 LinkedList<List<Integer>> list = new LinkedList<List<Integer>>();
 addLevel(list, 0, root);
 return list;
 }

 private void addLevel(LinkedList<List<Integer>> list, int level, TreeNode node) {
 if (node == null) return;
 if (list.size()-1 < level) list.addFirst(new LinkedList<Integer>());
 list.get(list.size()-1-level).add(node.val);
 addLevel(list, level+1, node.left);
 addLevel(list, level+1, node.right);
 }
 **/