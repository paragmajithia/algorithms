package com.leetcode.shortestpath;

import java.util.*;

/**
 * There are n cities connected by some number of flights.
 * You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that
 * there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops.
 * If there is no such route, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation: The graph is shown.
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 * Example 2:
 *
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation: The graph is shown.
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 104
 * There will not be any multiple flights between two cities.
 * 0 <= src, dst, k < n
 * src != dst
 */
public class CheapFlightsWithKStops {

    public static void main(String[] args) {
        CheapFlightsWithKStops fligths = new CheapFlightsWithKStops();

        // Bellmanford is better in performance compared to Djikstra's for this use case
        // as its similar to negative edge cycle
        System.out.println("Answer: " +
                fligths.findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1));


        System.out.println("Answer: " +
                fligths.findCheapestPriceUsingDjikstra(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1));
    }


    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;

        // Perform K hops
        for (int i = 0; i <= k; i++) {
            int[] pricesClone = prices.clone();
            // Travel from each visited source
            for (int j = 0; j < flights.length; j++) {
                int source = flights[j][0];
                int dest = flights[j][1];
                int price = flights[j][2];
                if (prices[source] == Integer.MAX_VALUE) {
                    continue;
                }
                pricesClone[dest] = Math.min(prices[source] + price, pricesClone[dest]);
            }
            prices = pricesClone;
        }
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }


    public int findCheapestPriceUsingDjikstra(int n, int[][] flights, int src, int dst, int k) {


        // Flight transit info
        // src -> [dest, price]
        Map<Integer, List<int[]>> transit = new HashMap<>();
        for (int i = 0; i < flights.length; i++) {
            int source = flights[i][0];
            int dest = flights[i][1];
            int price = flights[i][2];
            transit.computeIfAbsent(source, key -> new ArrayList<>()).add(new int[]{dest, price});
        }

        // BFS loop
        // Priority Que with least price ordering
        // stops, dest, price
        PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[2], b[2]);
        });
        que.offer(new int[]{0, src, 0});

        // Loop till queue is not empty
        while (!que.isEmpty()) {
            int[] node = que.poll();
            int stops = node[0];
            int dest = node[1];
            int price = node[2];

            if (dest == dst) {
                return price;
            }

            if (stops > k) {
                continue;
            }

            // Get all destinations and add to the que
            // only if stops are not greater than necessary
            if (transit.containsKey(dest)) {
                for (int[] newDest : transit.get(dest)) {

                    // New price
                    int newPrice = price + newDest[1];
                    int newDestInt = newDest[0];
                    que.offer(new int[]{stops + 1, newDestInt, newPrice});

                }
            }
        }
        return -1;

    }
}
