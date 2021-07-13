package com.leetcode;

import java.util.*;
/*
You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.



Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation:
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]


Constraints:

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
public class EvaluateDivision {

    public static void main(String[] args) {
        EvaluateDivision div = new EvaluateDivision();

        // Input
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList(new String[] {"a", "b"}));
        equations.add(Arrays.asList(new String[] {"b", "c"}));

        double[] values = new double[]{2,3};

        // Queries
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList(new String[] {"a", "c"}));
        queries.add(Arrays.asList(new String[] {"b", "a"}));
        queries.add(Arrays.asList(new String[] {"b", "e"}));

        System.out.println("Answer: " + Arrays.toString(div.calcEquation(equations, values, queries)));
    }

    public Map<String, Map<String, Double>> createGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> map = new HashMap<>();

        for (int i =0; i <equations.size(); i++) {
            List<String> expr = equations.get(i);

            // Add relation from 0 to 1
            Map<String, Double> value = map.getOrDefault(expr.get(0), new HashMap<String, Double>());
            value.put(expr.get(1), values[i]);
            map.put(expr.get(0), value);

            // Add relation from 1 to 0
            Map<String, Double> value2 = map.getOrDefault(expr.get(1), new HashMap<String, Double>());
            value2.put(expr.get(0), (1 / values[i]));
            map.put(expr.get(1), value2);

        }

        return map;

    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        // Create graph
        Map<String, Map<String, Double>> map = createGraph(equations, values);

        // Create response
        double[] answer = new double[queries.size()];

        // Process queries
        for (int i = 0; i < queries.size(); i++) {
            answer[i] = getAnswer(map, queries.get(i).get(0), queries.get(i).get(1), 1, new HashSet<String>());
        }

        return answer;

    }

    public double getAnswer(Map<String, Map<String, Double>> map, String source, String destination, double multiplier, Set<String> covered) {

        if (!map.containsKey(source)) {
            return -1;
        }

        if (map.get(source).containsKey(destination)) {
            return multiplier * map.get(source).get(destination);
        } else {
            for (String destKey: map.get(source).keySet()) {
                if (covered.contains(String.format("%s-%s", source, destKey))) {
                    continue;
                }
                covered.add(String.format("%s-%s", source, destKey));
                double ans = map.get(source).get(destKey) * getAnswer(map, destKey, destination, multiplier, covered);
                if (ans > 0) {
                    return ans;
                }
            }
        }

        return -1;
    }
}