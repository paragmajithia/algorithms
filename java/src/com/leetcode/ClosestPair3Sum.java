package com.leetcode;

import java.util.*;

public class ClosestPair3Sum {

    public static void main(String[] args) {
        System.out.println((new ClosestPair3Sum()).threeSumClosest(new int[] {0,2,1,-3}, 1));
    }
    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int closestDiff = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int optimum = target - nums[i];
            int low = i + 1;
            int high = nums.length -1;

            while (low < high) {
                // Check if diff is less
                int diff = Math.abs(optimum - (nums[low] + nums[high]));
                if (closestDiff > diff) {
                    closestDiff = diff;
                    ans = nums[low] + nums[high] + nums[i];

                    if (closestDiff == 0) {
                        return ans;
                    }
                }

                if (optimum - (nums[low] + nums[high]) > 0) {
                    low++;

                } else {
                    high--;
                }
            }

        }

        return ans;
    }
}
