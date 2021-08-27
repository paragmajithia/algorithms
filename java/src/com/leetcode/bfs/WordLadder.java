package com.leetcode.bfs;

import java.util.*;

/**
 * 127. Word Ladder
 * Hard
 *
 * 5801
 *
 * 1481
 *
 * Add to List
 *
 * Share
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 *
 *
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 * Example 2:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 *
 * Constraints:
 *
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 */
public class WordLadder {
    public static void main(String[] args) {
        WordLadder ladder = new WordLadder();
        // System.out.println("Answer:" + ladder.ladderLength("hit", "cog", Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"})));
        System.out.println("Answer:" + ladder.ladderLength("hot", "dog", Arrays.asList(new String[]{"hot","cog","dog","tot","hog","hop","pot","dot"})));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            Set<String> wordSet = new HashSet<String>(wordList);
            if (!wordSet.contains(endWord)) {
                return 0;
            }

            // PreProcess words (transitMap)
            // Ex: *ot ==> hot, dot
            // Ex: *og ==> log, cog
            Map<String, List<String>> transitMap = new HashMap<>();
            for (int i=0; i < wordList.size(); i++) {
                String word = wordList.get(i);
                for (int j=0; j< word.length(); j++) {
                    String newWord = word.substring(0,j) + "*" + ((j < (word.length() - 1))?word.substring(j+1):"");
                    transitMap.computeIfAbsent(newWord, key -> new ArrayList()).add(word);
                }
            }


            // BFS loop
            Set<String> visitedWords = new HashSet<String>();
            Queue<String> que = new LinkedList<>();
            que.offer(beginWord);

            Map<String, Integer> distance = new HashMap<>();
            distance.put(beginWord, 1);

            // Ex: hit ==> *it, h*t, hi*
            while (!que.isEmpty()) {

                String currWord = que.poll();
                for (int j=0; j< currWord.length(); j++) {
                    String newWord = currWord.substring(0,j) + "*" + ((j < (currWord.length() - 1))?currWord.substring(j+1):"");
                    visitedWords.add(currWord);
                    System.out.println("Current Word:" + currWord + ",Distance: " + distance);

                    // Check if there exists any transition
                    if (transitMap.containsKey(newWord)) {
                        for (String transitWord: transitMap.get(newWord)) {
                            if (transitWord.equals(endWord)) {
                                return distance.get(currWord) + 1;
                            }
                            if (!visitedWords.contains(transitWord)) {
                                distance.put(transitWord, distance.get(currWord) + 1);
                                que.offer(transitWord);
                            }

                        }
                    };
                }

            }
            return 0;
        }
    }