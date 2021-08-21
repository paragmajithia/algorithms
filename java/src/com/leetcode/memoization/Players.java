package com.leetcode.memoization;

import java.util.Arrays;

/**
 * 1900. The Earliest and Latest Rounds Where Players Compete
 * Hard
 *
 * 135
 *
 * 11
 *
 * Add to List
 *
 * Share
 * There is a tournament where n players are participating. The players are standing in a single row and are numbered from 1 to n based on their initial standing position (player 1 is the first player in the row, player 2 is the second player in the row, etc.).
 *
 * The tournament consists of multiple rounds (starting from round number 1). In each round, the ith player from the front of the row competes against the ith player from the end of the row, and the winner advances to the next round. When the number of players is odd for the current round, the player in the middle automatically advances to the next round.
 *
 * For example, if the row consists of players 1, 2, 4, 6, 7
 * Player 1 competes against player 7.
 * Player 2 competes against player 6.
 * Player 4 automatically advances to the next round.
 * After each round is over, the winners are lined back up in the row based on the original ordering assigned to them initially (ascending order).
 *
 * The players numbered firstPlayer and secondPlayer are the best in the tournament. They can win against any other player before they compete against each other. If any two other players compete against each other, either of them might win, and thus you may choose the outcome of this round.
 *
 * Given the integers n, firstPlayer, and secondPlayer, return an integer array containing two values, the earliest possible round number and the latest possible round number in which these two players will compete against each other, respectively.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 11, firstPlayer = 2, secondPlayer = 4
 * Output: [3,4]
 * Explanation:
 * One possible scenario which leads to the earliest round number:
 * First round: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
 * Second round: 2, 3, 4, 5, 6, 11
 * Third round: 2, 3, 4
 * One possible scenario which leads to the latest round number:
 * First round: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
 * Second round: 1, 2, 3, 4, 5, 6
 * Third round: 1, 2, 4
 * Fourth round: 2, 4
 * Example 2:
 *
 * Input: n = 5, firstPlayer = 1, secondPlayer = 5
 * Output: [1,1]
 * Explanation: The players numbered 1 and 5 compete in the first round.
 * There is no way to make them compete in any other round.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 28
 * 1 <= firstPlayer < secondPlayer <= n
 */
public class Players {
    public static void main(String[] args) {
        Players player = new Players();
        System.out.println(Arrays.toString(player.earliestAndLatest(11,2,4)));
    }
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {

        if (n == 2) {
            return new int[]{1,1};
        }

        // Set all n players bits to winning position
        int mask = (1 << n) - 1; // 1111
        int i = 1;
        int j = n;
        int round = 1;
        int[] resp = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};

        recurse(mask, 1, 0,n-1, firstPlayer-1, secondPlayer-1, resp, n);

        return resp;
    }

    public void recurse(int mask, int round, int start, int end,  int firstPlayer, int secondPlayer, int[] resp, int n) {

        System.out.println(String.format("Start:%s, End: %s, Round: %s, First: %s, Second: %s, Mask:%s, Resp:%s", start, end,
                round, firstPlayer, secondPlayer, Integer.toBinaryString(mask), Arrays.toString(resp)));
        if (start == firstPlayer && end == secondPlayer) {
            resp[0] = Math.min(resp[0], round);
            resp[1] = Math.max(resp[1], round);
        } else if (start < end) {

            if ((mask & (1 << start)) == 0) {
                recurse(mask, round, start+1, end, firstPlayer, secondPlayer, resp, n);
            } else if ((mask & (1 << end)) == 0) {
                recurse(mask, round, start, end-1, firstPlayer, secondPlayer, resp, n);
            } else {
                // start looses
                if (start != firstPlayer && start != secondPlayer) {
                    recurse(mask ^ (1 << start), round, start+1, end-1, firstPlayer, secondPlayer, resp, n);
                }


                // end looses
                if (end != firstPlayer && end != secondPlayer) {
                    recurse(mask ^ (1 << end), round, start+1, end-1, firstPlayer, secondPlayer, resp, n);
                }
            }



        } else {
            recurse(mask, round+1, 0, n-1, firstPlayer, secondPlayer, resp, n);
        }
    }
}