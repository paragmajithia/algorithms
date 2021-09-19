package com.leetcode.stack;

/**
 * 456. 132 Pattern
 * Medium
 *
 * 2661
 *
 * 150
 *
 * Add to List
 *
 * Share
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
 *
 * Return true if there is a 132 pattern in nums, otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: false
 * Explanation: There is no 132 pattern in the sequence.
 * Example 2:
 *
 * Input: nums = [3,1,4,2]
 * Output: true
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 * Example 3:
 *
 * Input: nums = [-1,3,2,0]
 * Output: true
 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 2 * 105
 * -109 <= nums[i] <= 109
 */

import javafx.util.Pair;
import java.util.*;

public class Pattern {

    public boolean find132pattern(int[] nums) {

        // Return false if there are less than 3 elements
        if (nums.length < 3) {
            return false;
        }

        // Keep stack of intervals to check
        // if given value at index satisfies 132 pattern from previous intervals
        // Previous intervals ==> Min1 - Max1, Min2-Max2 where min2 < min 1
        ArrayDeque<Pair<Integer,Integer>> stack = new ArrayDeque<>();

        // Loop through all elements and check if index at given element satisfies 132 patter
        for (int i = 0; i < nums.length; i++) {

            // Stack is empty or current element is less than min of last element
            // Then create new interval as new minimum is found with new max required
            if (stack.isEmpty() || nums[i] < stack.getLast().getKey()) {
                stack.addLast(new Pair(nums[i], nums[i]));

            }
            // Case where current element is greater than min of last interval
            else if (nums[i] > stack.getLast().getKey() ) {
                // return true if this element falls between last interval
                if (nums[i] < stack.getLast().getValue()) {
                    return true;
                } else if (nums[i] > stack.getLast().getValue()) {
                    // Pop the last pair out as interval needs to be updated
                    Pair<Integer, Integer> oldPair = stack.removeLast();

                    // Compare the num with previous intervals and return true if condition satisfies
                    // Else remove the previous interval from stack as current interval is high enough
                    // Provided last interval max is less than current internval max
                    while (!stack.isEmpty() && nums[i] >= stack.getLast().getValue()) {
                        stack.removeLast();
                    }
                    // At this time, nums[i] < stack.peek().max (if stack not empty)
                    // Just compare min now
                    if (!stack.isEmpty() && nums[i] > stack.getLast().getKey()) {
                        return true;
                    }

                    // If condition not satisfied yet
                    // Push the updated interval with new max back to stack
                    stack.addLast(new Pair(oldPair.getKey(), nums[i]));
                }
            }

        }

        // retrun false by default
        return false;

    }

    // BRUTE FORCE SOLUTION
    public boolean brutForceSolution(int[] nums) {
        int min_i = Integer.MAX_VALUE;
        for (int j = 0; j < nums.length - 1; j++) {
            min_i = Math.min(min_i, nums[j]);
            for (int k = j + 1; k < nums.length; k++) {
                if (nums[k] < nums[j] && min_i < nums[k])
                    return true;
            }
        }
        return false;
    }
}
