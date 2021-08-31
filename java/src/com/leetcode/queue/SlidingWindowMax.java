package com.leetcode.queue;

import java.util.*;

/**
 * 239. Sliding Window Maximum
 * Hard
 *
 * 7015
 *
 * 258
 *
 * Add to List
 *
 * Share
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Example 3:
 *
 * Input: nums = [1,-1], k = 1
 * Output: [1,-1]
 * Example 4:
 *
 * Input: nums = [9,11], k = 2
 * Output: [11]
 * Example 5:
 *
 * Input: nums = [4,-2], k = 2
 * Output: [4]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */
public class SlidingWindowMax {

    public static void main(String[] args) {
        SlidingWindowMax max = new SlidingWindowMax();
        System.out.println(Arrays.toString(max.maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3)));
        System.out.println(Arrays.toString(max.maxSlidingWindow(new int[] {4,-2}, 2))); ;
    }

    public static class Node implements Comparable<Node> {
        int val;
        int pos;

        public Node(int val, int pos) {
            this.val = val;
            this.pos = pos;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node node = (Node) obj;
                if (this.val == node.val && this.pos == node.pos) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.pos;
        }
        public int compareTo(Node node) {

            if (node.val > this.val) {
                return 1;
            } else if (node.val < this.val) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {

        int[] resp = new int[nums.length - k + 1];
        PriorityQueue<Node> que = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            Node node = new Node(nums[i], i);
            que.offer(node);
        }


        resp[0] = que.peek().val;

        int start = 1;
        int curr = k;
        while (curr < nums.length) {
            Node node = new Node(nums[curr], curr);
            que.offer(node);
            // Remove old positions
            while (!que.isEmpty() && que.peek().pos < start) {
                que.poll();
            }
            resp[start] = que.peek().val;
            curr++;
            start++;
        }
        return resp;
    }
}
