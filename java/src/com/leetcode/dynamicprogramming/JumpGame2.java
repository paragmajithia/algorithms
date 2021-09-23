package com.leetcode.dynamicprogramming;

/**
 * 45. Jump Game II
 * Medium
 *
 * 5777
 *
 * 221
 *
 * Add to List
 *
 * Share
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 */
public class JumpGame2 {

    public int jump(int[] nums) {

        // Windows based approach
        // Check from current position the next region / window to scan
        // Fomr that window -- check the max it can reach
        // The max will be end of new window in next loop

        // Return if there is no kump required as length is only 1
        if (nums.length <= 1) {
            return 0;
        }

        // Initialize first window
        int left = 0;
        int right = 0;
        int max = 0;
        int jump = 0;

        while (left <= right) {

            // Check the max end the current window can reach
            for (int j = left; j <= right; j++) {
                max = Math.max(max, j + nums[j]);
            }

            jump++;
            // Check if next window already covers end
            if (max >=  (nums.length - 1)) {
                return jump;
            }

            // Repeat for next window
            left = right + 1;
            right = max;
        }

        return -1;
    }
}
