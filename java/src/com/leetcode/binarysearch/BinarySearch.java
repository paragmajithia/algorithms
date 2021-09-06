package com.leetcode.binarysearch;

/*
Given an array of integers nums which is sorted in ascending order, and an integer target,
 write a function to search target in nums. If target exists, then return its index.
 Otherwise, return -1.

Example:
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4

You must write an algorithm with O(log n) runtime complexity.
 */
public class BinarySearch {
    public static void main(String[] args) {
        // Pass sorted array
        System.out.println((new BinarySearch()).search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
    }

    public int search(int[] nums, int target) {
        if (nums.length <= 0)
            return -1;
        return search(nums, target, 0, nums.length - 1);
    }

    // Recursive function
    public int search(int[] nums, int target, int start, int end) {
        if (start == end && end < nums.length && nums[start] == target) return start;
        if (start >= end) return -1;

        int mid = (start + end) / 2;

        // Divide
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return search(nums, target, start, mid - 1);
        } else {
            return search(nums, target, mid + 1, end);
        }

    }
}
