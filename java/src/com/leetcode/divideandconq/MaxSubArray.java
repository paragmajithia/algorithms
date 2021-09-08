package com.leetcode.divideandconq;

/**
 * 53. Maximum Subarray
 * Easy
 *
 * 14287
 *
 * 676
 *
 * Add to List
 *
 * Share
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1
 * Example 3:
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * -105 <= nums[i] <= 105
 *
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {

        // Let current max and sum be first element
        int currentMax = nums[0];
        int sum = currentMax;

        for (int i =1; i < nums.length; i++) {
            // Increase the sum only if previous cumulative sum is positive
            // as if its negative then it will only decrease the cumulative sum further
            if (sum < 0) {
                // Reset the sum to current element as soon as previous sum gets to 0
                // as previous values will now not add any value
                sum = nums[i];
            } else {
                // Continue to add new element to sum if previous cumulative sum is non negative
                sum = sum + nums[i];
            }

            // Get Max
            currentMax = Math.max(sum, currentMax);
        }

        return currentMax;

    }
}
