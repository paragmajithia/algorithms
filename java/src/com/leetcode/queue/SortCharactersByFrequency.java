package com.leetcode.queue;

import java.util.*;
import java.util.stream.*;

/**
 * 451. Sort Characters By Frequency
 * Medium
 *
 * 2841
 *
 * 157
 *
 * Add to List
 *
 * Share
 * Given a string s, sort it in decreasing order based on the frequency of characters, and return the sorted string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "tree"
 * Output: "eert"
 * Explanation: 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cccaaa"
 * Output: "aaaccc"
 * Explanation: Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * Example 3:
 *
 * Input: s = "Aabb"
 * Output: "bbAa"
 * Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 105
 * s consists of English letters and digits.
 */
public class SortCharactersByFrequency {

    public String frequencySort(String s) {

        // Get count of characters in String s
        Map<Character, Long> countMap = s.chars().mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(obj -> obj, Collectors.counting()));

        // Add the counts to priority queue
        PriorityQueue<Map.Entry<Character, Long>> que =
                new PriorityQueue<>((mapEntry1, mapEntry2) -> {
                    if (mapEntry1.getValue() > mapEntry2.getValue()) {
                        return -1;
                    } else if (mapEntry1.getValue() < mapEntry2.getValue()) {
                        return 1;
                    } else {
                        return 0;
                    }
                });
        que.addAll(countMap.entrySet());

        StringBuilder sb = new StringBuilder();
        while (!que.isEmpty()) {

            Map.Entry<Character, Long> entry = que.poll();
            char[] charArray = new char[entry.getValue().intValue()];
            Arrays.fill(charArray, entry.getKey());
            sb.append(charArray);
        }
        return sb.toString();

    }
}
