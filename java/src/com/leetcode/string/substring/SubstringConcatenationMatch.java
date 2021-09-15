package com.leetcode.string.substring;

/**
 * 30. Substring with Concatenation of All Words
 * Hard
 *
 * 1476
 *
 * 1617
 *
 * Add to List
 *
 * Share
 * You are given a string s and an array of strings words of the same length. Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.
 *
 * You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 * Example 2:
 *
 * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * Output: []
 * Example 3:
 *
 * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * Output: [6,9,12]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of lower-case English letters.
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] consists of lower-case English letters.
 */

import java.util.*;

public class SubstringConcatenationMatch {

    public List<Integer> findSubstring(String s, String[] words) {

        // Response list
        List<Integer> resp = new ArrayList<>();
        // Word count
        Map<String, Integer> countMap = new HashMap<>();
        for (int i =0 ; i < words.length; i++) {
            countMap.put(words[i], countMap.getOrDefault(words[i], 0) + 1);
        }

        // check for each possible index
        int totalLength = words.length * words[0].length();
        int lastIndex = s.length() - totalLength;
        for (int i = 0; i <= lastIndex ; i++) {
            String strToCheck = s.substring(i, i + totalLength);
            if (hasAllWords(strToCheck, countMap, words[0].length())) {
                resp.add(i);
            }
        }

        // Return response
        return resp;

    }

    public boolean hasAllWords(String s, Map<String, Integer> countMap, int len) {

        // Track current count
        Map<String, Integer> curMap = new HashMap<>();

        for (int i = 0; i < s.length(); i=i+len) {
            String chkWord = s.substring(i, i+len);

            // Return false if word not found
            if (!countMap.containsKey(chkWord)) {
                return false;
            }

            // Return false if word count found is > expected
            curMap.put(chkWord, curMap.getOrDefault(chkWord, 0) + 1);
            if (curMap.get(chkWord) > countMap.get(chkWord)) {
                return false;
            }

        }

        return true;
    }
}
