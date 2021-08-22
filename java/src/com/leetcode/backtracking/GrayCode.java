package com.leetcode.backtracking;

/**
 * An n-bit gray code sequence is a sequence of 2n integers where:
 *
 * Every integer is in the inclusive range [0, 2n - 1],
 * The first integer is 0,
 * An integer appears no more than once in the sequence,
 * The binary representation of every pair of adjacent integers differs by exactly one bit, and
 * The binary representation of the first and last integers differs by exactly one bit.
 * Given an integer n, return any valid n-bit gray code sequence.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: [0,1,3,2]
 * Explanation:
 * The binary representation of [0,1,3,2] is [00,01,11,10].
 * - 00 and 01 differ by one bit
 * - 01 and 11 differ by one bit
 * - 11 and 10 differ by one bit
 * - 10 and 00 differ by one bit
 * [0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
 * - 00 and 10 differ by one bit
 * - 10 and 11 differ by one bit
 * - 11 and 01 differ by one bit
 * - 01 and 00 differ by one bit
 * Example 2:
 *
 * Input: n = 1
 * Output: [0,1]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 16
 */

import java.util.*;

public class GrayCode {

    public List<Integer> grayCode(int n) {

        // Response and max size
        List<Integer> resp = new ArrayList<Integer>();
        resp.add(0);
        int maxSize = (int) Math.pow(2, n);
        Set<Integer> processed = new HashSet<Integer>();
        processed.add(0);

        // Recurse and back track
        recurse(n, maxSize, resp, processed);

        return resp;

    }

    public boolean recurse(int n, int maxSize, List<Integer> resp, Set<Integer> processed) {

        if ((resp.size() == maxSize) && checkMaxDiff(resp.get(0), resp.get(resp.size() - 1))) {
            return true;
        } else if (resp.size() == maxSize) {
            return false;
        }

        // Get last number
        int lastNum = resp.get(resp.size() -1);

        // Keep flipping till results are valid
        for (int i = 0; i < n; i++) {

            // Flip the ith bit
            int newNum = flipBit(lastNum, i);
            if (!processed.contains(newNum)) {

                resp.add(newNum);
                processed.add(newNum);
                boolean ret = recurse(n, maxSize, resp, processed);
                if (ret == Boolean.TRUE) {
                    return true;
                } else {
                    resp.remove(resp.size() - 1);
                    processed.remove(newNum);
                }
            }
        }

        return false;
    }

    public int flipBit(int num, int pos) {
        return (num ^ (1 << pos));
    }

    public boolean checkMaxDiff(int num1, int num2) {
        int diff = num1 ^ num2;
        return (diff != 0 && (diff & (diff - 1)) == 0) ;
    }
}
