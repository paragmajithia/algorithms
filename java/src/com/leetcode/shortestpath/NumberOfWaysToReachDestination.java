package com.leetcode.shortestpath;

/**

 * You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.
 *
 * You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
 *
 * Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
 * Output: 4
 * Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
 * The four ways to get there in 7 minutes are:
 * - 0 ➝ 6
 * - 0 ➝ 4 ➝ 6
 * - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
 * - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
 * Example 2:
 *
 * Input: n = 2, roads = [[1,0,10]]
 * Output: 1
 * Explanation: There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 200
 * n - 1 <= roads.length <= n * (n - 1) / 2
 * roads[i].length == 3
 * 0 <= ui, vi <= n - 1
 * 1 <= timei <= 109
 * ui != vi
 * There is at most one road connecting any two intersections.
 * You can reach any intersection from any other intersection.
 */
/*
SOLUTION EXPLANATION:

From the problem statement it was clear we need to apply dijkistra to find shortest path between source and dest.

Well, dijkistra to rescue. Using dijkistra we can keep shortest path by keep relaxing the edges. (Cormen lingo).

Catch is, whenver you find a better way to reach a particular vertex update the number of ways we can reach this vertex same as number of ways we can reach parent vertex.

If we arrive at a vetrex, with same time from parent, we will add the parent's number of ways to the current vertex's number of ways i.e line dp[v[0]]+=dp[u] in the code

In the End the last veretx will have total number of ways from origin i.e 0

In my solution array named "ways" stores the number of ways from i-th vertex to 0th vertex.

 */
import java.util.*;

public class NumberOfWaysToReachDestination {

    public int countPaths(int n, int[][] roads) {

        // Initialize distance to max
        int[] distance = new int[n];
        int[] ways = new int[n];
        distance[0] = 0;
        ways[0] = 1;
        for (int i =0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        // Create map for graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i< roads.length; i++) {
            int u = roads[i][0];
            int v = roads[i][1];
            int time = roads[i][2];
            graph.computeIfAbsent(u, key -> new ArrayList<>()).add(new int[] {v, time});
            graph.computeIfAbsent(v, key -> new ArrayList<>()).add(new int[] {u, time});
        }

        // Queue with element as [node, distanceFrom_0]
        PriorityQueue<int[]> que = new PriorityQueue<int[]>( (a,b) -> (a[1] - b[1]) );
        que.offer(new int[] {0, 0});

        while (!que.isEmpty()) {
            int[] element = que.poll();
            int node = element[0];
            int dist = element[1];

            // Skip if the distance pulled from que is more than found so far
            if (dist > distance[node] ) {
                continue;
            }

            // Go through each destination
            for (int[] destArr: graph.getOrDefault(node, Collections.emptyList())) {

                // If destination distance becomes less using this edge
                if (dist + destArr[1] < distance[destArr[0]] ) {
                    distance[destArr[0]] = dist + destArr[1];
                    ways[destArr[0]] = ways[node];
                    que.offer(new int[] {destArr[0], distance[destArr[0]]});
                } else if (dist + destArr[1] == distance[destArr[0]]) {
                    // If destination distance is smae using this edge
                    ways[destArr[0]] = ways[destArr[0]] + ways[node];
                    ways[destArr[0]] = ways[destArr[0]] % 1_000_000_007;
                }

            }

        }

        return ways[n-1]% 1_000_000_007;
    }


}