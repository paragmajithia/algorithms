package com.leetcode.binarysearch;

import java.util.Arrays;

/**
 * Search in Rotated Sorted Array
 * Medium
 *
 * 10564
 *
 * 774
 *
 * Add to List
 *
 * Share
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * Example 3:
 *
 * Input: nums = [1], target = 0
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -104 <= target <= 104
 */
public class SearchRotatedArray {

    public static void main(String[] args) {
        SearchRotatedArray main = new SearchRotatedArray();
        // System.out.println(main.search(new int[] {11, 14, 15, 18, 20, 24, 26, 0, 2, 8, 10}, 2));
        System.out.println(main.search(new int[] {1,3}, 4));
    }

    public int search(int[] nums, int target) {

        System.out.println("Input array: " + Arrays.toString(nums) + " Search Int: " + target);
        return search(nums, target, 0, nums.length -1);

    }

    public int search(int[] nums, int target, int start, int end) {

        // Base cases
        System.out.println("Searching between index: " + start + " and " + end);
        int mid = start + (end-start)/2;

        if (start > end) {
            return -1;
        } else if (target == nums[start]) {
            return start;
        } else if (target == nums[end]) {
            return end;
        } else if (target == nums[mid]) {
            return mid;
        } else if (start == end) {
            return -1;
        }


        // Recurse for right part of the array
        if(     // Usual NO rotation case where higher number should be on right
                (target > nums[mid] && target < nums[end]) ||
                // Rotated but higher number still goes to right ex: search 26 in array 11 14 15 18 20 24 26 0 2 8 10
                (target > nums[mid] && nums[end] < nums[mid]) ||
                // Rotated and lower number falls to right of mid ex: search 0 in array 11 14 15 18 20 24 26 0 2 8 10
                (target < nums[mid] && target < nums[start] && nums[mid] > nums[start] )) {

            return search(nums, target, mid + 1, end - 1);

        } // Recurse for left part of array
        else if (
                // Usual NO rotation case where lower number should be on left
                (target < nums[mid] && target > nums[start]) ||
                // Rotated but lower number still goes to left ex: search 0 in array 24 26 0 2 8 10 11 14 15 18 20
                (target < nums[mid] && nums[mid] < nums[start]) ||
                // Rotated and higher number falls to left of mid ex: search 26 in array 24 26 0 2 8 10 11 14 15 18 20
                (target > nums[mid] && target > nums[end] && nums[end] > nums[mid])) {
            return search(nums, target, start + 1, mid - 1);
        }
        return -1;

    }
}
