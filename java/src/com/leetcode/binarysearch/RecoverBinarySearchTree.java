package com.leetcode.binarysearch;

/**
 * 99. Recover Binary Search Tree
 * Medium
 *
 * 3192
 *
 * 126
 *
 * Add to List
 *
 * Share
 * You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
 * Example 2:
 *
 *
 * Input: root = [3,1,4,null,null,2]
 * Output: [2,1,4,null,null,3]
 * Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 1000].
 * -231 <= Node.val <= 231 - 1
 *
 *
 * Follow up: A solution using O(n) space is pretty straight-forward. Could you devise a constant O(1) space solution?
 */
/*

GUIDE ---------- SOLUTION EXPLANATION ---------------
intuition: exactly two nodes are misplaced given a bst
we can simply look at binary search tree as a sorted array and we know there are two elements misplaced


1 2 3 4 5 misplaced as--> 1 4 3 2 5

the inorder traversal is same as linear scan a sorted array, to find the two misplaced value:
firstOne = element before the small element (which is 4, as we meet 3 as small)
secondOne = second small element (which is 2)

boundary case:
1 2 3 4 5 misplaced as --> 1 3 2 4 5, first one is 3 and second one is 2, so we spot small value only once
*/

public class RecoverBinarySearchTree {

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


    public void recoverTree(TreeNode root) {

        TreeNode[] helper = new TreeNode[3];
        //to handle the misplaced value is the smallest # in the tree, that is leftMost node
        //use a dummy as Integer.MIN_VALUE
        TreeNode dummy = new TreeNode(Integer.MIN_VALUE);
        helper[0] = dummy;

        // InOrder traversal
        recurse(root, helper);

        // Swap 2 nodes val
        int temp = helper[1].val;
        helper[1].val = helper[2].val;
        helper[2].val = temp;

        return;


    }

    //helper = {previous, firstOne, secondOne}
    public void recurse(TreeNode node, TreeNode[] helper) {

        if (node == null) {
            return;
        }

        // Go to left subtree
        recurse(node.left, helper);

        // Check if misplacement found
        // if spot a small value
        if (node.val < helper[0].val) {
            if (helper[1] == null) {
                //firstOne = previous node
                helper[1] = helper[0];
            }
            // firstOne is found, now use else statement here in case of firstOne and secondOne are adjacent
            if (helper[1] != null) {
                //secondOne = current node
                helper[2] = node;
            }
        }

        // Add current process node as previous node
        helper[0] = node;

        // Go to right sub tree
        recurse(node.right, helper);

    }
}
