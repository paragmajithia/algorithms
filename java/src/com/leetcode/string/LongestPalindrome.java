package com.leetcode.string;
/*
5. Longest Palindromic Substring
Medium

13252

793

Add to List

Share
Given a string s, return the longest palindromic substring in s.



Example 1:

Input: s = "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
Example 3:

Input: s = "a"
Output: "a"
Example 4:

Input: s = "ac"
Output: "a"


Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
 */
public class LongestPalindrome {

    public String longestPalindrome(String s) {

        if (s.length() == 1) {
            return s;
        }

        // Loop through each index and check if give centre is good for palindrome
        int maxLen = 0;
        int centreind = 0;
        for (int i = 0; i < s.length(); i++) {

            int length1 = getMaxLengthPal(s, i, i, s.length() - 1);
            int length2 = getMaxLengthPal(s, i, i+1, s.length() - 1);
            if (length1 > maxLen || length2 > maxLen) {
                maxLen = Math.max(length1, length2);
                centreind = i;
            }
        }
        // index = 4 == > length = 3 ==> 3,4,5
        // index = 5 == > length = 5 ==> 3,4,5,6,7
        // index = 4 == > length = 4 ==> 3,4,5,6
        // index = 5 == > length = 4 ==> 4,5,6,7
        return s.substring((centreind - (maxLen-1)/ 2) , 1 + centreind + maxLen/ 2);


    }

    public int getMaxLengthPal(String s, int start, int end, int max) {

        while (start >= 0 && end <= max && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        return end - start - 1;

    }
}