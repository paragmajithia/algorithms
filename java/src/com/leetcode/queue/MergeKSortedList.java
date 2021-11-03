package com.leetcode.queue;

/**
 * 23. Merge k Sorted Lists
 * Hard
 *
 * 9114
 *
 * 393
 *
 * Add to List
 *
 * Share
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 *
 *
 * Example 1:
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 *
 * Input: lists = []
 * Output: []
 * Example 3:
 *
 * Input: lists = [[]]
 * Output: []
 *
 *
 * Constraints:
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length won't exceed 10^4.
 */

import java.util.*;

class MergeKSortedList {

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeKLists(ListNode[] lists) {

        // Return if list size is empty or with size 1
        if (lists == null || lists.length == 0) {
            return null;
        } else if (lists.length == 1) {
            return lists[0];
        }

        // Priority Queue to add all nodes
        PriorityQueue<ListNode> que = new PriorityQueue<>(lists.length, (a,b) -> a.val - b.val);
        for (int i =0; i < lists.length; i++) {
            if (lists[i] != null) {
                que.add(lists[i]);
            }
        }


        // Dummy node & tail node
        ListNode dummy = new ListNode();
        ListNode tailNode = dummy;

        // Pick the node with least number and add to response node
        while (!que.isEmpty()) {

            ListNode node = que.poll();
            tailNode.next = node;
            tailNode = tailNode.next;

            if (node.next != null) {
                que.add(node.next);
            }
        }

        // Return head
        return dummy.next;
    }
}
