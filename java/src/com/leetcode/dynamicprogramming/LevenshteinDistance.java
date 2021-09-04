package com.leetcode.dynamicprogramming;

/**
 * 72. Edit Distance
 * Hard
 *
 * 6503
 *
 * 75
 *
 * Add to List
 *
 * Share
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 *
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 *
 * Constraints:
 *
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters.
 */
public class LevenshteinDistance {
    public static void main(String[] args) {
        LevenshteinDistance main = new LevenshteinDistance();

        System.out.println("Edit required : " + main.minDistance("horse", "ros"));
    }
    public int minDistance(String word1, String word2) {

        if (word1 == null || word2 == null) {
            return -1;
        }

        if (word1.isEmpty()) {
            return word2.length();
        }
        if (word2.isEmpty()) {
            return word1.length();
        }

        int[][] cache = new int[word1.length()][word2.length()];
        for (int i =0; i < word1.length(); i++) {
            for (int j=0; j < word2.length(); j++) {
                cache[i][j] = -1;
            }
        }
        return minRecur(word1.toCharArray(), word2.toCharArray(), 0, 0, cache);

    }

    public int minRecur(char[] word1, char[] word2, int id1, int id2, int[][] cache) {

        if (id1 >= word1.length) {
            return word2.length - id2;
        }
        if (id2 >= word2.length) {
            return word1.length - id1;
        }

        if (cache[id1][id2] != -1) {
            return cache[id1][id2];
        }

        // Character matches
        if (word1[id1] == word2[id2]) {
            cache[id1][id2] = minRecur(word1, word2, id1 + 1, id2 + 1, cache);

            // Character doesnt match
        } else {

            // Case 1 ==> Insert on first word.
            // Hence compare same position with next position of second word
            int insert = minRecur(word1, word2, id1, id2 + 1, cache);

            // Case 2 ==> Delete char on first word.
            // Hence compare next position with next same char of second word
            int delete = minRecur(word1, word2, id1 + 1, id2, cache);

            // Case 3 ==> Replace the char of first word
            // Hence compare next char of both the words
            int replace = minRecur(word1, word2, id1 + 1, id2 + 1, cache);

            // Total count is current operation (1) + minimum count for above -- post order traversal
            cache[id1][id2] = 1 + Math.min(insert, Math.min(delete, replace));
        }

        return cache[id1][id2];
    }
}
