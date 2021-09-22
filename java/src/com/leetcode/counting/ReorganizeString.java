package com.leetcode.counting;

/**
 * 767. Reorganize String
 * Medium
 *
 * 3573
 *
 * 161
 *
 * Add to List
 *
 * Share
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 *
 * Return any possible rearrangement of s or return "" if not possible.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: "aba"
 * Example 2:
 *
 * Input: s = "aaab"
 * Output: ""
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of lowercase English letters.
 */

import java.util.*;
import javafx.util.*;

public class ReorganizeString {

    // Efficient solution O(N)
    public String reorganizeStringEfficient(String S) {
        if (S == null || S.length() == 1) return S;

        Map<Character, Integer> dic = new HashMap<>();
        char maxChar = S.charAt(0);
        int L = S.length();

        // count chars in map, get max
        for (char c: S.toCharArray()) {
            dic.put(c, dic.getOrDefault(c, 0) +1);
            if (dic.get(c) > dic.get(maxChar)) {
                maxChar = c;
            }
        }

        if (dic.get(maxChar) > (L+1)/2) return "";

        int idx = 0;
        char[] ret = new char[L];

        // put all maxChar's into array (may not reach end of S)
        while (idx < L && dic.get(maxChar) > 0) {
            ret[idx] = maxChar;
            dic.put(maxChar, dic.get(maxChar)-1);
            idx +=2;
        }

        // loop through dic, may go through a key where val is 0, but won't do anything
        for (Character c: dic.keySet()) {
            while (dic.get(c) > 0) {
                if (idx >= L) idx = 1; // First time it reaches L, reset it. Won't be inf loop cause not looping on idx
                ret[idx] = c;
                dic.put(c, dic.get(c)-1);
                idx += 2;
            }
        }
        return new String(ret);
    }

    // Using priority que
    public String reorganizeString(String s) {

        // Count of characters
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            countMap.put(s.charAt(i), countMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        // ELement has array character, count
        PriorityQueue<Pair<Character, Integer>> que = new PriorityQueue<>((a,b) -> {

            if (b.getValue() > a.getValue()) {
                return 1;
            } else if (b.getValue() < a.getValue()) {
                return -1;
            }
            return 0;
        });

        // Put all elements in que
        for (Map.Entry<Character, Integer> entry: countMap.entrySet()) {
            que.offer(new Pair(entry.getKey(), entry.getValue()));
        }

        if (que.peek().getValue() > ((s.length() + 1)/2) )  {
            return "";
        }

        // Loop through qued elements
        StringBuilder sb = new StringBuilder();
        while (!que.isEmpty()) {

            Pair<Character, Integer> firstPair = que.poll();

            // Add first element
            if (sb.length() == 0 || sb.charAt(sb.length() - 1) != firstPair.getKey()) {
                sb.append(firstPair.getKey());
                if (firstPair.getValue() > 1) {
                    que.offer(new Pair(firstPair.getKey(), firstPair.getValue() - 1));
                }
            } // Add second element
            else {
                Pair<Character, Integer> secondPair = que.poll();
                sb.append(secondPair.getKey());
                if (secondPair.getValue() > 1) {
                    que.offer(new Pair(secondPair.getKey(), secondPair.getValue() - 1));
                }
                que.offer(firstPair);
            }
        }

        return sb.toString();

    }
}
