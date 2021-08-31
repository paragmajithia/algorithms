package com.leetcode.shortestpath;

/**
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
 *
 * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
 *
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
 * Output: 0.25000
 * Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
 * Example 2:
 *
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
 * Output: 0.30000
 * Example 3:
 *
 *
 *
 * Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
 * Output: 0.00000
 * Explanation: There is no path between 0 and 2.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 10^4
 * 0 <= start, end < n
 * start != end
 * 0 <= a, b < n
 * a != b
 * 0 <= succProb.length == edges.length <= 2*10^4
 * 0 <= succProb[i] <= 1
 * There is at most one edge between every two nodes.
 */

import javafx.util.Pair;

import java.util.*;

public class PathWithMaxProbability {

    public static void main(String[] args) {
        PathWithMaxProbability prob = new PathWithMaxProbability();
        System.out.println("Answer: " + prob.maxProbability(3, new int[][] {{0,1}, {1,2}, {0,2}},
                new double[] {0.5, 0.5, 0.2}, 0,2));
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {

        // Create graph structure
        Map<Integer, Map<Integer, Double>> graph = new HashMap<>();
        for (int i =0; i < edges.length; i++) {
            int source = edges[i][0];
            int dest = edges[i][1];
            double weight = succProb[i];
            graph.computeIfAbsent(source, key -> new HashMap<>()).put(dest, weight);
            graph.computeIfAbsent(dest, key -> new HashMap<>()).put(source, weight);
        }

        // Priority que to get path with max probability
        // Element has source, probability
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Pair<Integer, Double>> que = new PriorityQueue<>((a, b) -> {
            if (a.getValue() < b.getValue()) {
                return 1;
            } else if (a.getValue() > b.getValue()) {
                return -1;
            } else {
                return 0;
            }
        });
        que.offer(new Pair(start, 1.0));


        while (!que.isEmpty()) {
            Pair<Integer, Double> node = que.poll();
            if (node.getKey() == end) {
                return node.getValue();
            }
            visited.add(node.getKey());
            System.out.println("Node Pulled: " + node + ", Visited: " + visited);

            // Add all next elements to the priority queue
            if (graph.containsKey(node.getKey())) {
                Map<Integer, Double> neighbours = graph.get(node.getKey());
                for (Integer newDest: neighbours.keySet()) {
                    if (!visited.contains(newDest)) {
                        double newProb = neighbours.get(newDest) * node.getValue();
                        System.out.println("New Dest: " + newDest + ", New Prob: " + newProb);
                        que.offer(new Pair(newDest, newProb));
                    }
                }
            }
        }

        return 0;
    }
}
