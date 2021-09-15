package com.leetcode.binarytree;

/**
 * 109. Convert Sorted List to Binary Search Tree
 * Medium
 *
 * 3746
 *
 * 104
 *
 * Add to List
 *
 * Share
 * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
 * Example 2:
 *
 * Input: head = []
 * Output: []
 * Example 3:
 *
 * Input: head = [0]
 * Output: [0]
 * Example 4:
 *
 * Input: head = [1,3]
 * Output: [3,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in head is in the range [0, 2 * 104].
 * -105 <= Node.val <= 105
 */

import java.util.*;

public class SortedListToBinarySearchTree {
    // Below def already given in question
    public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

/**
 * Definition for a binary tree node.
 */
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


    /**
     * =================================================
     * SOLUTION STARTS HERE
     * =============================================
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {

        // Add the head to ArrayList
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        return getCentreNode(list, 0, list.size() - 1, list.size() - 1);
    }

    public TreeNode getCentreNode(List<Integer> list, int start, int end, int max) {

        if (start > end || start < 0 || end > max) {
            return null;
        } else if (start == end) {
            return new TreeNode(list.get(start), null, null);
        }

        // End is 8 Or 9 ==> returns 4, End
        // Atmost right should be on higher
        int mid = (start + (end -start) / 2);

        return (new TreeNode(list.get(mid),
                getCentreNode(list, start, mid - 1, max),
                getCentreNode(list, mid + 1, end, max)
        ));

    }

    /**
     * ANOTHER SOLUTION -- THIS IS SPACE EFFICIENT
     * @param head
     * @return
     */
    public TreeNode sortedListToBSTSPACEEFFICIENT(ListNode head) {
        return toBST(head,null);
    }

    public TreeNode toBST(ListNode head, ListNode tail) {
        if(head == tail) return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != tail && fast.next.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode n = new TreeNode(slow.val);
        n.left = toBST(head, slow);
        n.right = toBST(slow.next, tail);
        return n;
    }
}