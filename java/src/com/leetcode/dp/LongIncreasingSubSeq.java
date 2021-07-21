package com.leetcode.dp;
/*
Given an integer array nums, return the number of longest increasing subsequences.

Notice that the sequence has to be strictly increasing.



Example 1:

Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:

Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.



Constraints:

1 <= nums.length <= 2000
-106 <= nums[i] <= 106
 */

import java.util.*;

class LongIncreasingSubSeq {
    public int findNumberOfLIS(int[] nums) {

        // To store max length of sequence till given index
        int[] dp = new int[nums.length];

        // To store count of sequences that has max sequence length
        int[] dc = new int[nums.length];

        // Fill values
        Arrays.fill(dp, 1);
        Arrays.fill(dc, 1);

        // Update sequence counts
        int maxLen = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) {
                    continue;
                }
                if (dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    dc[i] = dc[j];
                    if (dp[i] > maxLen) maxLen = dp[i];
                } else if (dp[i] == dp[j] + 1) {
                    dc[i] = dc[i] + dc[j];
                }
            }
        }

        int counts = 0;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] == maxLen) {
                counts += dc[i];
            }
        }
        return counts;

    }
}
