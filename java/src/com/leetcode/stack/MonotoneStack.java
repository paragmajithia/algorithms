package com.leetcode.stack;

/**
 * 402. Remove K Digits
 * Medium
 *
 * 3855
 *
 * 164
 *
 * Add to List
 *
 * Share
 * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 *
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 *
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 *
 *
 * Constraints:
 *
 * 1 <= k <= num.length <= 105
 * num consists of only digits.
 * num does not have any leading zeros except for the zero itself.
 */

import java.util.*;
public class MonotoneStack {

    public static void main(String[] args) {
        MonotoneStack main = new MonotoneStack();
        System.out.println("Answer: " + main.removeKdigits("10200", 1));
    }

    public String removeKdigits(String num, int k) {

        // Edge case
        if (num.equals("0") || num.length() == k) {
            return "0";
        }

        // Use stack
        // Monotonic stack -- the stack should be in increasing (or decreasing order)
        ArrayDeque<Character> deq = new ArrayDeque<>();

        // Loop through complete string
        // Compare current character with last character in stack
        // Delete last character in stack till the current character is found to be less than previous character in stack
        for (int i = 0; i < num.length(); i++) {
            Character currChar = num.charAt(i);  // Ex: 1432219 -- gets 1

            while (k > 0 && deq.peekLast() != null && deq.peekLast() > currChar) {
                deq.pollLast();
                k--;
            }
            deq.addLast(currChar); // Pushes 1, 4 // 1,3 // 1,2 // 1,2,2
        }

        // if k is still pending remove last elements
        while (k > 0) {
            deq.pollLast();
            k--;
        }

        // Return new string
        StringBuilder sb = new StringBuilder();
        System.out.println("Initial String value: " + sb.toString() + ", length: " + sb.toString().length());
        while (!deq.isEmpty()) {
            Character currChar = deq.pollFirst();
            System.out.println("String value: " + sb + ", Character value: " + currChar);
            if (currChar.equals('0') && sb.toString().length() == 0) {
                continue;
            }
            sb.append(currChar);
        }
        return (sb.toString().length() > 0?sb.toString():"0");

    }
}
