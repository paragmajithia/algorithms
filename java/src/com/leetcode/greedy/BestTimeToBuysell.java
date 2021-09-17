package com.leetcode.greedy;

/**
 * 122. Best Time to Buy and Sell Stock II
 * Medium
 *
 * 5337
 *
 * 2182
 *
 * Add to List
 *
 * Share
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 *
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
 *
 * Find and return the maximum profit you can achieve.
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit is 4 + 3 = 7.
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Total profit is 4.
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 3 * 104
 * 0 <= prices[i] <= 104
 * Accepted
 */
public class BestTimeToBuysell {

    public int maxProfit(int[] prices) {

        int min = prices[0];
        int index = 1;
        int profit = 0;

        // Loop through all prices of the day
        while (index < prices.length) {

            // Case --  you get largest number
            if (prices[index] >= prices[index-1]) {
                if (index == (prices.length -1)) {
                    profit = profit + (prices[index] - min);
                }
                index++;
                continue;
            } else if (prices[index] < prices[index-1]) {
                // Case you got smaller number
                // Book profit for earlier slot and start new min
                profit = profit + (prices[index-1] - min);
                min = prices[index];
            }
            index++;
        }

        return profit;

    }
}
