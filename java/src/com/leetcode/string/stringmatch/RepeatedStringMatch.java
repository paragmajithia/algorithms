package com.leetcode.string.stringmatch;

/**
 * 686. Repeated String Match
 * Medium

 * Add to List
 *
 * Share
 * Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a substring of it.
 * If it is impossible for b​to be a substring of a after repeating it, return -1.
 *
 * Notice: string "abc" repeated 0 times is "",  repeated 1 time is "abc" and repeated 2 times is "abcabc".
 *
 *
 *
 * Example 1:
 *
 * Input: a = "abcd", b = "cdabcdab"
 * Output: 3
 * Explanation: We return 3 because by repeating a three times "abcdabcdabcd", b is a substring of it.
 * Example 2:
 *
 * Input: a = "a", b = "aa"
 * Output: 2
 * Example 3:
 *
 * Input: a = "a", b = "a"
 * Output: 1
 * Example 4:
 *
 * Input: a = "abc", b = "wxyz"
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= a.length <= 104
 * 1 <= b.length <= 104
 * a and b consist of lower-case English letters.
 */

public class RepeatedStringMatch {

    public static void main(String[] args) {
        RepeatedStringMatch matcher = new RepeatedStringMatch();
        System.out.println(matcher.repeatedStringMatch("abcd", "cdabcdab"));
    }
    public int repeatedStringMatch(String a, String b) {

        // Case where b's length is greater than a's length
        if (b.length() <= a.length() && a.indexOf(b) != -1) {
            return 1;
        } else if (b.length() <= a.length() && String.format("%s%s", a,a).indexOf(b) != -1) {
            return 2;
        } else if (b.length() <= a.length()) {
            return -1;
        }

        // appden  a till it reaches length of b
        StringBuilder sb = new StringBuilder(a);
        int count = 1;
        while (sb.toString().length() < b.length()) {
            sb.append(a);
            count++;
        }

        if (sb.toString().indexOf(b) != -1) {
            return count;
        }

        System.out.println("At end sb: " + sb.toString());
        return (sb.append(a).toString().indexOf(b) != -1?count+1: -1);



    }
}