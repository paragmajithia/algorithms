package com.leetcode;

import java.util.*;

/*
Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]


Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {

        // add first empty set {}
        List<List<Integer>> output = new ArrayList<>();
        output.add(new ArrayList<Integer>());

        for (int i = 0; i < nums.length; i++) {
            // Pick the new element (ex: 2)
            List<List<Integer>> newSetList = new ArrayList<>();

            // Loop through each existing set
            for (int j = 0; j < output.size(); j++) {
                // Add new element to each set thats already created
                // ex: {}, {1}
                List<Integer> newSet = new ArrayList<Integer>(output.get(j));
                newSet.add(nums[i]);
                newSetList.add(newSet);
            }
            output.addAll(newSetList);
            // {}
            // {} {1}
            // {} {1} {2} {1,2}

        }

        return output;
    }
}
