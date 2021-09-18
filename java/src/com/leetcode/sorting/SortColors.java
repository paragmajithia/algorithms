package com.leetcode.sorting;

import java.util.Arrays;

/**
 Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

 We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

 You must solve this problem without using the library's sort function.



 Example 1:

 Input: nums = [2,0,2,1,1,0]
 Output: [0,0,1,1,2,2]
 Example 2:

 Input: nums = [2,0,1]
 Output: [0,1,2]
 Example 3:

 Input: nums = [0]
 Output: [0]
 Example 4:

 Input: nums = [1]
 Output: [1]


 Constraints:

 n == nums.length
 1 <= n <= 300
 nums[i] is 0, 1, or 2.


 Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */

public class SortColors{
    public static void main(String[] args) {
        SortColors main = new SortColors();
        int[] arrToSort = new int[] {2,0,2,1,1,0};
        main.sortColors(arrToSort);
        System.out.println("Sorted Array: " + Arrays.toString(arrToSort));

        arrToSort = new int[] {2,0,1};
        main.sortColors(arrToSort);
        System.out.println("Sorted Array: " + Arrays.toString(arrToSort));

        arrToSort = new int[] {0};
        main.sortColors(arrToSort);
        System.out.println("Sorted Array: " + Arrays.toString(arrToSort));

        arrToSort = new int[] {1};
        main.sortColors(arrToSort);
        System.out.println("Sorted Array: " + Arrays.toString(arrToSort));

        arrToSort = new int[] {2,2,2,2,1,1,1,0,0,0,1,1,1,2};
        main.sortColors(arrToSort);
        System.out.println("Sorted Array: " + Arrays.toString(arrToSort));
    }
    public void sortColors(int[] nums) {

        int firstWhite = Integer.MAX_VALUE, firstBlue = Integer.MAX_VALUE;

        for (int i=0; i < nums.length; i++) {
            // System.out.println("Before loop index " + i + " First White: " + firstWhite + ", First Blue: " + firstBlue + ", Array: " + Arrays.toString(nums));
            // Case Blue is found
            if (nums[i] == 2 && firstBlue == Integer.MAX_VALUE) {
                firstBlue = i;
            }

            // Case white is found
            // Swap with first blue
            if (nums[i] == 1) {
                if (firstWhite == Integer.MAX_VALUE) {
                    firstWhite = i;
                }
                if (firstBlue != Integer.MAX_VALUE) {
                    int temp = nums[i];
                    nums[i] = nums[firstBlue];
                    nums[firstBlue] = temp;
                    firstBlue++;
                    firstWhite = Math.min(firstWhite, firstBlue-1);
                }
            }


            // Case when red is found
            // first swap with blue and then again swap with white
            int indexToSwap = i;
            if (nums[i] == 0 && firstBlue != Integer.MAX_VALUE) {
                int temp = nums[i];
                nums[i] = nums[firstBlue];
                nums[firstBlue] = temp;
                indexToSwap = firstBlue;
                firstBlue++;
            }
            if (nums[indexToSwap] == 0 && firstWhite != Integer.MAX_VALUE) {
                int temp = nums[indexToSwap];
                nums[indexToSwap] = nums[firstWhite];
                nums[firstWhite] = temp;
                firstWhite++;
            }
            // System.out.println("After loop index " + i + " First White: " + firstWhite + ", First Blue: " + firstBlue + ", Array: " + Arrays.toString(nums));

        }

    }

    /**
     * Other quicker solution
     * @param nums
     */
    public void sortColorsSOLUTION2(int[] nums) {
        // 1-pass
        int p1 = 0, p2 = nums.length - 1, index = 0;
        while (index <= p2) {
            if (nums[index] == 0) {
                nums[index] = nums[p1];
                nums[p1] = 0;
                p1++;
            }
            if (nums[index] == 2) {
                nums[index] = nums[p2];
                nums[p2] = 2;
                p2--;
                index--;
            }
            index++;
        }
    }
}