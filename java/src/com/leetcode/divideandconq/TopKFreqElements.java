package com.leetcode.divideandconq;

/**
 * 347. Top K Frequent Elements
 * Medium
 *
 * 5855
 *
 * 291
 *
 * Add to List
 *
 * Share
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 *
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */

import java.util.*;

public class TopKFreqElements {

    public int[] topKFrequent(int[] nums, int k) {

        // Get frequency O(n)
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i= 0; i < nums.length; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }

        // Add to List (element, count) O(n)
        List<int[]> countList = new ArrayList();
        for (Integer key: countMap.keySet()) {
            countList.add(new int[] {key, countMap.get(key)});
        }

        // O(n log n)
        Collections.sort(countList, (a,b) -> {
            return Integer.compare(b[1], a[1]);
        });

        return countList.subList(0,k).stream().mapToInt(a -> a[0]).toArray();

    }
}
