package com.leetcode.bitmask;

/**
 * 421. Maximum XOR of Two Numbers in an Array
 * Medium
 *
 * 2496
 *
 * 233
 *
 * Add to List
 *
 * Share
 * Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,10,5,25,2,8]
 * Output: 28
 * Explanation: The maximum result is 5 XOR 25 = 28.
 * Example 2:
 *
 * Input: nums = [0]
 * Output: 0
 * Example 3:
 *
 * Input: nums = [2,4]
 * Output: 6
 * Example 4:
 *
 * Input: nums = [8,10,2]
 * Output: 10
 * Example 5:
 *
 * Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * Output: 127
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 105
 * 0 <= nums[i] <= 231 - 1
 */

import java.util.*;

public class MaximumXOROf2NumbersInArray {

    public int findMaximumXOR(int[] nums) {

        int max = 0;
        int mask = 0;

        // There are Max 31 bits
        for (int i = 30; i >= 0;i--) {

            // Mask would change in each iteration
            // 1000.., 11000.. , 11100..
            mask = mask | (1 << i);

            // We want to compare only left most bits
            // Hence keep set to compare left most bits only
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }

            // Check if we get get max XOR at ith bit
            // tmp is the destination maximum xor to check (till ith bit from left)
            int tmp = max | (1 << i);

            for(int prefix : set){

                //This is the most tricky part, coming from a fact that if a ^ b = c, then a ^ c = b;
                // Note: a ^ b = c ==> a ^ b ^ b = c ^ b ==> a = c ^ b
                // now we have the 'c', which is greedyTry 9destination), and we have the 'a', which is prefix
                // If we hope the formula a ^ b = c to be valid, then we need the b,
                // and to get b, we need a ^ c, if a ^ c exisited in our set, then we're good to go
                // So the idea is, we change the problem to:
                // Find two numbers in num, the xor of which equals to a given number n.
                // Because this number is given, we don't need to do n^2 search, instead we try to find if n xor one num in nums = another num in nums (xor trick). So we can do it in one pass.
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }

        return max;
    }
}