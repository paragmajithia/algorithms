package com.leetcode.prefixsum;

/*
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.



Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]


Constraints:

2 <= nums.length <= 105
-30 <= nums[i] <= 30
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.


Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)

 */
public class ProductExceptItself {
    public int[] productExceptSelf(int[] nums) {

        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];

        // Update prefix array
        prefix[0] = nums[0];
        for (int i=1; i < nums.length; i++) {
            prefix[i] = prefix[i-1] * nums[i];
        }


        // Update suffix array
        suffix[nums.length - 1] = nums[nums.length - 1];
        for (int j=nums.length - 2; j >= 0; j--) {
            suffix[j] = suffix[j+1] * nums[j];
        }

        int[] output = new int[nums.length];
        output[0] = suffix[1];
        output[nums.length - 1] = prefix[nums.length - 2];
        for (int k = 1; k < nums.length - 1; k++) {
            output[k] = prefix[k-1] * suffix[k+1];
        }

        return output;
    }
}
