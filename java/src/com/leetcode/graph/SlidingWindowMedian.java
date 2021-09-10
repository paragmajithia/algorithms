package com.leetcode.graph;

import java.util.*;
import java.util.function.*;

/**
 * 480. Sliding Window Median
 * Hard
 *
 * 1708
 *
 * 111
 *
 * Add to List
 *
 * Share
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.
 *
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 * You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 * Explanation:
 * Window position                Median
 * ---------------                -----
 * [1  3  -1] -3  5  3  6  7        1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7        3
 *  1  3  -1  -3 [5  3  6] 7        5
 *  1  3  -1  -3  5 [3  6  7]       6
 * Example 2:
 *
 * Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
 * Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 105
 * 231 <= nums[i] <= 231 - 1
 */
public class SlidingWindowMedian {

    public static void main(String[] args) {
        SlidingWindowMedian main = new SlidingWindowMedian();
        //System.out.println("Answer:" + Arrays.toString(main.medianSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3)));
        // System.out.println("Answer:" + Arrays.toString(main.medianSlidingWindow(new int[] {1,4,2,3}, 4)));
        // System.out.println("Answer:" + Arrays.toString(main.medianSlidingWindow(new int[] {2,2}, 2)));
        System.out.println("Answer:" + Arrays.toString(main.medianSlidingWindow(new int[] {2147483647,2147483647}, 2)));
    }

    public double[] medianSlidingWindow(int[] nums, int k) {

        // Maintain left and Right Sorted set
        // We used sorted set instead of priority queue as insertion / removal is O(log k) in sorted set
        // In priority queue removal by element is O(k)
        // We add index in set instead of elements as elements can be repeated and you cant add same element in set
        // Comparison though would be based on elements pointed by the set
        Comparator<Integer> comp = (i1, i2) -> (nums[i1] != nums[i2])?Integer.compare(nums[i1], nums[i2]): i1 - i2;
        TreeSet<Integer> leftSet = new TreeSet<>(comp);
        TreeSet<Integer> rightSet = new TreeSet<>(comp);

        // add all k indexes to the left set
        for (int i = 0; i < k; i++) {
            leftSet.add(i);
        }

        // Runnable consumer to maintain balance on number of elements on left and right set
        // Moves element from left to right
        // Right tree will have size greater than or equal to left
        Runnable balancer = () -> {
           while (leftSet.size() > rightSet.size()) {
                // Move the largest element from left to right
                rightSet.add(leftSet.pollLast());
            }
        };
        // Balance left and right tree
        balancer.run();

        // Supplier to get median
        Supplier<Double> medianSupp = k%2 == 0?
                () -> (((double)(nums[leftSet.last()])+nums[rightSet.first()])/2) :
                () -> (double) nums[rightSet.first()];


        // Response array -- Add first median
        double[] resp = new double[nums.length - k + 1];
        resp[0] = medianSupp.get();

        // Loop through remaining elements, balance and return the median
        for (int j = k, start = 1; j < nums.length; j++, start++) {

            // Remove the head element
            if (!leftSet.remove(start-1)) {
                rightSet.remove(start-1);
            }

            // Add the next element such that left tree has more elements to run balancer
            rightSet.add(j);
            leftSet.add(rightSet.pollFirst());

            // Balance left and right tree
            balancer.run();

            // Get the median
            resp[start] =  medianSupp.get();

        }

        return resp;
    }
}
