package com.leetcode.string;

import java.util.Arrays;

/*
97. Interleaving String
Medium

3088

162

Add to List

Share
Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.



Example 1:


Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Example 3:

Input: s1 = "", s2 = "", s3 = ""
Output: true


Constraints:

0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200
s1, s2, and s3 consist of lowercase English letters.
 */
public class InterLeavingString {

    public static void main(String[] args) {
        InterLeavingString main = new InterLeavingString();
        //System.out.println("Answer: " + main.isInterleave("a", "d", "ad"));
        // System.out.println("Answer: " + main.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println("Answer: " + main.isInterleave("abab", "baba", "abababab"));
//        System.out.println("Answer: " + main.isInterleave(
//                "abababababababababababababababababababababababababababababababababababababababababababababababababbb",
//                "babababababababababababababababababababababababababababababababababababababababababababababababaaaba",
//                "abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababbb"));
    }

    public boolean isInterleave(String s1, String s2, String s3) {

        // Base condition
        if ((s1 == null && s2 == null && s3 == null) ||
                (s1.isEmpty() && s2.isEmpty() && s3.isEmpty())) {
            return true;
        } else if ( (s1.length() + s2.length()) != s3.length()) {
            return false;
        }
        int[][] memo = new int[s1.length()+1][s2.length()+1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j ++) {
                memo[i][j] = -1;
            }
        }

        return  recurseStr(s1,s2,s3,0, 0, 0, memo);
    }

    public boolean recurseStr(String s1, String s2, String s3, int firstStrInd, int secondStrInd, int thirdStrInd, int[][] memo) {

        System.out.println(String.format("Frist:%s, Second: %s, third: %s, F: %d, S: %d, T: %d, memo:%s",
                 s1,s2,s3,firstStrInd, secondStrInd, thirdStrInd, Arrays.deepToString(memo)));

        if (thirdStrInd >= s3.length()) {
            memo[firstStrInd][secondStrInd] = 1;
            return true;
        }

        if (memo[firstStrInd][secondStrInd] >= 0) {
            return memo[firstStrInd][secondStrInd] == 1;
        }

        boolean ans = false;
        // check if both matched
        if (secondStrInd < s2.length() && firstStrInd < s1.length() && s1.charAt(firstStrInd) == s3.charAt(thirdStrInd) && s2.charAt(secondStrInd) == s3.charAt(thirdStrInd)) {
            ans = recurseStr(s1,s2,s3,firstStrInd+1, secondStrInd, thirdStrInd+1, memo) ||
                    recurseStr(s1,s2,s3,firstStrInd, secondStrInd+1, thirdStrInd+1, memo);
        }

        // Check if first string match and second did not
        if (!ans && firstStrInd < s1.length() && s1.charAt(firstStrInd) == s3.charAt(thirdStrInd)
                && (secondStrInd >= s2.length() || s2.charAt(secondStrInd) != s3.charAt(thirdStrInd))) {
            ans = recurseStr(s1,s2,s3,firstStrInd+1, secondStrInd, thirdStrInd+1, memo);
        }

        // Check if second matched and first did not
        if (!ans && secondStrInd < s2.length() && s2.charAt(secondStrInd) == s3.charAt(thirdStrInd)
                && (firstStrInd >= s1.length() || s1.charAt(firstStrInd) != s3.charAt(thirdStrInd) )) {
            ans = recurseStr(s1,s2,s3,firstStrInd, secondStrInd+1, thirdStrInd+1, memo);
        }

        memo[firstStrInd][secondStrInd] = ans == true? 1:0;
        return ans;

    }
}
