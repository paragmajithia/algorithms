package com.leetcode;
/*Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.



Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9


Constraints:

n == height.length
0 <= n <= 3 * 104
0 <= height[i] <= 105

 */
import java.util.*;

class TrappingRainWater {
    public static void main(String[] args) {
        (new TrappingRainWater()).trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1});
    }
    public int trap(int[] height) {

        Deque<Integer> stack = new ArrayDeque<Integer>();
        int ans = 0;
        // Loop thorugh each elevation
        for (int i=0; i< height.length; i++) {

            while (!stack.isEmpty() && height[i] > height[stack.getLast()]) {
                // Get last wall with smaller size
                int curr = stack.removeLast();
                if (stack.isEmpty()) {
                    break;
                }
                // Distance is from current ith wall to previous wall
                int distance = i - stack.getLast() -1;
                int bound = Math.min(height[i], height[stack.getLast()]) - height[curr];
                ans = ans + bound * distance;

            }

            stack.addLast(i);

        }
        return ans;

    }
}
