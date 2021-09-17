package com.leetcode.topologysort;

/**
 * 802. Find Eventual Safe States
 * Medium
 *
 * 1471
 *
 * 283
 *
 * Add to List
 *
 * Share
 * We start at some node in a directed graph, and every turn, we walk along a directed edge of the graph. If we reach a terminal node (that is, it has no outgoing directed edges), we stop.
 *
 * We define a starting node to be safe if we must eventually walk to a terminal node. More specifically, there is a natural number k, so that we must have stopped at a terminal node in less than k steps for any choice of where to walk.
 *
 * Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
 *
 * The directed graph has n nodes with labels from 0 to n - 1, where n is the length of graph. The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph, going from node i to node j.
 *
 *
 *
 * Example 1:
 *
 * Illustration of graph
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Explanation: The given graph is shown above.
 * Example 2:
 *
 * Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
 * Output: [4]
 *
 *
 * Constraints:
 *
 * n == graph.length
 * 1 <= n <= 104
 * 0 <= graph[i].length <= n
 * graph[i] is sorted in a strictly increasing order.
 * The graph may contain self-loops.
 * The number of edges in the graph will be in the range [1, 4 * 104].
 */

import java.util.*;

public class EventualSafeStates {

    public List<Integer> eventualSafeNodes(int[][] graph) {

        // Incoming nodes for given nodes (Reverse graph)
        Map<Integer, Set<Integer>> incomingNodes = new HashMap<>();
        // Count of out going edges
        Map<Integer, Integer> countOutGoingEdges = new HashMap<>();
        LinkedList<Integer> que = new LinkedList<>();

        // Preprocess data
        for (int i = 0; i < graph.length; i++) {
            // Add 0 outgoing nodes to que
            if (graph[i].length == 0) {
                que.offer(i);
                continue;
            }
            countOutGoingEdges.put(i, graph[i].length);
            for (int j = 0; j < graph[i].length; j++) {
                incomingNodes.computeIfAbsent(graph[i][j], key -> new HashSet<>()).add(i);
            }
        }

        // Loop through queu with least outgoing edges
        List<Integer> ans = new ArrayList<>();
        while (!que.isEmpty()) {
            int node = que.poll();
            ans.add(node);

            // Get incoming nodes set
            Set<Integer> nodeSet = incomingNodes.get(node);
            if (nodeSet == null || nodeSet.isEmpty()) continue;

            // Decrement count of all incoming nodes
            for (Iterator<Integer> itr = incomingNodes.get(node).iterator(); itr.hasNext();) {

                int item = itr.next();
                int newcount = countOutGoingEdges.compute(item, (key, value) -> {
                    return value - 1;
                });
                if (newcount == 0) {
                    que.offer(item);
                    itr.remove();
                }
            }
        }

        // Return the ans
        Collections.sort(ans);
        return ans;
    }
}