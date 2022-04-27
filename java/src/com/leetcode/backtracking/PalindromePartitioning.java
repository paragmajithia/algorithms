package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Palindrome Partitioning
 * Medium
 * Share
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 *
 * A palindrome string is a string that reads the same backward as forward.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 *
 * Input: s = "a"
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 */
class PalindromePartitioning {

    public static void main(String[] args) {
        PalindromePartitioning obj = new PalindromePartitioning();
        System.out.println(obj.partition("aab"));
    }
    public List<List<String>> partition(String s) {

        // Find at all possible position start/end if given substring is palindrome
        // Ex: dp[0][5] = true means string starting at index 0 and ending at 5 is paindrome
        boolean[][] dp = new boolean[s.length()][s.length()];

        // Move end index in outer look -- one at a time
        // Substring is palindrom if first and last character of substring is same
        // and inner substring is palindrome as well
        for (int end = 0; end < s.length();end++ ) {
            for (int start = 0; start <= end; start++) {
                if ( s.charAt(start) == s.charAt(end) &&
                        ((end - start) <=2  || dp[start+1][end-1]) ) {
                    dp[start][end] = true;
                }
            }
        }

        // backtracking -- check for partition at each position and backtrack
        List<List<String>> responseList = new ArrayList<>();
        List<String> currentList = new ArrayList<>();
        dfs(s, 0, currentList, responseList, dp);

        return responseList;
    }

    public void dfs(String s, int start, List<String> currentList, List<List<String>> responseList, boolean[][] dp) {

        // Base case
        if (start >= s.length()) {
            responseList.add(new ArrayList<>(currentList));
            return;
        }

        // Check options
        int end = start;
        while (end < s.length()) {

            if (dp[start][end]) {
                currentList.add(s.substring(start, end+1));
                dfs(s, end+1, currentList, responseList, dp);
                currentList.remove(currentList.size() - 1);
            }
            end++;

        }

    }
}
