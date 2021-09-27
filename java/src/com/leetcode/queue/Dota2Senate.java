package com.leetcode.queue;

/**
 * 649. Dota2 Senate
 * Medium
 * <p>
 * 369
 * <p>
 * 279
 * <p>
 * Add to List
 * <p>
 * Share
 * In the world of Dota2, there are two parties: the Radiant and the Dire.
 * <p>
 * The Dota2 senate consists of senators coming from two parties. Now the Senate wants to decide on a change in the Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can exercise one of the two rights:
 * <p>
 * Ban one senator's right: A senator can make another senator lose all his rights in this and all the following rounds.
 * Announce the victory: If this senator found the senators who still have rights to vote are all from the same party, he can announce the victory and decide on the change in the game.
 * Given a string senate representing each senator's party belonging. The character 'R' and 'D' represent the Radiant party and the Dire party. Then if there are n senators, the size of the given string will be n.
 * <p>
 * The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.
 * <p>
 * Suppose every senator is smart enough and will play the best strategy for his own party. Predict which party will finally announce the victory and change the Dota2 game. The output should be "Radiant" or "Dire".
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: senate = "RD"
 * Output: "Radiant"
 * Explanation:
 * The first senator comes from Radiant and he can just ban the next senator's right in round 1.
 * And the second senator can't exercise any rights anymore since his right has been banned.
 * And in round 2, the first senator can just announce the victory since he is the only guy in the senate who can vote.
 * Example 2:
 * <p>
 * Input: senate = "RDD"
 * Output: "Dire"
 * Explanation:
 * The first senator comes from Radiant and he can just ban the next senator's right in round 1.
 * And the second senator can't exercise any rights anymore since his right has been banned.
 * And the third senator comes from Dire and he can ban the first senator's right in round 1.
 * And in round 2, the third senator can just announce the victory since he is the only guy in the senate who can vote.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == senate.length
 * 1 <= n <= 104
 * senate[i] is either 'R' or 'D'.
 */

import java.util.*;

public class Dota2Senate {

    public String predictPartyVictory(String senate) {

        // Keep Radiant and dire in separate que
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();
        int totalLen = senate.length();
        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }

        // Poll each senate from both and compare the index
        while (radiant.size() > 0 && dire.size() > 0) {
            int r1 = radiant.poll();
            int d1 = dire.poll();

            if (r1 < d1) {
                radiant.offer(r1 + totalLen);
            } else {
                dire.offer(d1 + totalLen);
            }
        }

        return (radiant.size() > dire.size() ? "Radiant" : "Dire");

    }

    /**
     * OTHER SIMPLE NUT INEFFICIENT SOLUTION
     * @param senate
     * @return
     */
    public String predictPartyVictoryOTHER(String senate) {
        int r = 0, d = 0, start = 0;
        char[] arr = senate.toCharArray();
        for (char c : arr) {
            if (c == 'R') r++;
            else d++;
        }

        while (r > 0 && d > 0) {
            while (arr[start] != 'R' && arr[start] != 'D') {
                start = (start + 1) % arr.length;
            }

            char ban = 'R';
            if (arr[start] == 'R') {
                ban = 'D';
                d--;
            } else {
                r--;
            }
            int idx = (start + 1) % arr.length;
            while (arr[idx] != ban) {
                idx = (idx + 1) % arr.length;
            }

            arr[idx] = ' ';
            start = (start + 1) % arr.length;
        }

        return d == 0 ? "Radiant" : "Dire";
    }
}
