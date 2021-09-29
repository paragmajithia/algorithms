package com.leetcode.graph;

import java.util.*;

/*
There is an undirected weighted connected graph. You are given a positive integer n which denotes that the graph has n nodes labeled from 1 to n,
and an array edges where each edges[i] = [ui, vi, weighti] denotes that there is an edge between nodes ui and vi with weight equal to weighti.

A path from node start to node end is a sequence of nodes [z0, z1, z2, ..., zk] such that z0 = start and zk = end
and there is an edge between zi and zi+1 where 0 <= i <= k-1.

The distance of a path is the sum of the weights on the edges of the path.
Let distanceToLastNode(x) denote the shortest distance of a path between node n and node x.
A restricted path is a path that also satisfies that distanceToLastNode(zi) > distanceToLastNode(zi+1) where 0 <= i <= k-1.

Return the number of restricted paths from node 1 to node n. Since that number may be too large, return it modulo 109 + 7.

Example 1:


Input: n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
Output: 3
Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The three restricted paths are:
1) 1 --> 2 --> 5
2) 1 --> 2 --> 3 --> 5
3) 1 --> 3 --> 5
Example 2:


Input: n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
Output: 1
Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The only restricted path is 1 --> 3 --> 7.


Constraints:

1 <= n <= 2 * 104
n - 1 <= edges.length <= 4 * 104
edges[i].length == 3
1 <= ui, vi <= n
ui != vi
1 <= weighti <= 105
There is at most one edge between any two nodes.
There is at least one path between any two nodes.

 */
public class RestrictedPaths {

    public static void main(String[] args) {
        RestrictedPaths paths = new RestrictedPaths();

        int[][] edges = new int[7][];
        edges[0] = new int[]{1, 2, 3};
        edges[1] = new int[]{1, 3, 3};
        edges[2] = new int[]{2, 3, 1};
        edges[3] = new int[]{1, 4, 2};
        edges[4] = new int[]{5, 2, 2};
        edges[5] = new int[]{3, 5, 1};
        edges[6] = new int[]{5, 4, 10};
        System.out.println(paths.countRestrictedPaths(5, edges));
    }


    public int countRestrictedPaths(int n, int[][] edges) {

        // Build graph
        Map<Integer, List<int[]>> graph = buildGraph(edges);

        // Store distance of each node and visited tracker
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        long[] restrictedPathCount = new long[n + 1];
        restrictedPathCount[n] = 1;

        // Queue has array of 2 elements {dist, node}
        // Pick min distance node
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });
        minHeap.offer(new int[]{0, n});

        // Calculate distance of all nodes till n
        while (n > 0) {
            int[] currNode = minHeap.poll();
            int currDist = currNode[0];
            int currNum = currNode[1];
            if (visited[currNum]) {
                continue;
            }
            ;
            visited[currNum] = true;
            dist[currNum] = currDist;
            n--;

            for (int[] edge : graph.get(currNum)) {
                if (visited[edge[0]] && dist[currNum] > dist[edge[0]]) {
                    restrictedPathCount[currNum] = (restrictedPathCount[currNum] + restrictedPathCount[edge[0]]) % 1_000_000_007;
                } else if (!visited[edge[0]]) {
                    minHeap.offer(new int[]{(currDist + edge[1]), edge[0]});
                }

            }
        }

        return (int) restrictedPathCount[1];

    }


    // Build graph
    private Map<Integer, List<int[]>> buildGraph(int[][] edges) {
        // Value is {node, weight}
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int startNode = edges[i][0];
            int endNode = edges[i][1];
            int weight = edges[i][2];
            graph.computeIfAbsent(startNode, key -> {
                return new ArrayList<>();
            }).add(new int[]{endNode, weight});

            graph.computeIfAbsent(endNode, key -> {
                return new ArrayList<>();
            }).add(new int[]{startNode, weight});
        }
        return graph;
    }
}
