package com.leetcode;

import java.util.*;

/*
You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.

All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.

For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.



Example 1:


Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
Output: ["JFK","MUC","LHR","SFO","SJC"]
Example 2:


Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.


Constraints:

1 <= tickets.length <= 300
tickets[i].length == 2
fromi.length == 3
toi.length == 3
fromi and toi consist of uppercase English letters.
fromi != toi
 */
public class ItenaryReconstruct {

    public static void main(String[] args) {
        ItenaryReconstruct iten = new ItenaryReconstruct();
        List<List<String>> list = new ArrayList<>();

        list.add(new ArrayList<String>(Arrays.asList(new String[] {"MUC","LHR"})));
        list.add(new ArrayList<String>(Arrays.asList(new String[] {"JFK","MUC"})));
        list.add(new ArrayList<String>(Arrays.asList(new String[] {"SFO","SJC"})));
        list.add(new ArrayList<String>(Arrays.asList(new String[] {"LHR","SFO"})));

        // New test
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"EZE","TIA"})));
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"EZE","HBA"})));
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"AXA","TIA"})));
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"JFK","AXA"})));
//
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"ANU","JFK"})));
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"ADL","ANU"})));
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"TIA","AUA"})));
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"ANU","AUA"})));
//
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"ADL","EZE"})));
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"ADL","EZE"})));
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"EZE","ADL"})));
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"AXA","EZE"})));
//
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"AUA","AXA"})));
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"JFK","AXA"})));
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"AXA","AUA"})));
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"AUA","ADL"})));
//
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"ANU","EZE"})));
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"TIA","ADL"})));
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"EZE","ANU"})));
//        list.add(new ArrayList<String>(Arrays.asList(new String[] {"AUA","ANU"})));

        System.out.println("Output: " + iten.findItinerary(list));


    }

    public boolean checkDestinations(String source, List<String> ans, Map<String, List<String>> graph,
                                     Map<String, List<Boolean>> availEdges, int availCount) {

//        System.out.println("============");
//        System.out.println("Graph: " + graph);
//        System.out.println("Avail Edges: " + availEdges);
//        System.out.println("Avail Count: " + availCount);

        if (availCount == 0) {
            return true;
        }

        List<String> destList = graph.get(source);
        if (destList == null || destList.isEmpty()) {
            return false;
        }

        for (int i=0; i < destList.size(); i++) {
            // Edge not available to process
            if (!availEdges.get(source).get(i)) {
                continue;
            }
            availEdges.get(source).set(i ,false);
            boolean isValid = checkDestinations(graph.get(source).get(i), ans, graph, availEdges, availCount-1);
            if (isValid) {
                ans.add(graph.get(source).get(i));
                return true;
            }
            // Make edge available
            availEdges.get(source).set(i ,true);

        }


        return false;
    }

    public List<String> findItinerary(List<List<String>> tickets) {

        Map<String, List<String>> graph = new HashMap<>();
        Map<String, List<Boolean>> availEdges = new HashMap<>();

        // Construct graph
        for (int i = 0; i < tickets.size(); i++) {
            String source = tickets.get(i).get(0);
            String dest = tickets.get(i).get(1);
            List<String> destList = graph.getOrDefault(source, new ArrayList<String>());
            List<Boolean> availList = availEdges.getOrDefault(source, new ArrayList<Boolean>());
            destList.add(dest);
            availList.add(true);
            graph.put(source, destList);
            availEdges.put(source, availList);
        }

        // Sort destination for each source
        for (String source : graph.keySet()) {
            Collections.sort(graph.get(source));
        }

        // Reconstruct itenary
        List<String> ans = new ArrayList();
        List<String> destList = graph.get("JFK");
        checkDestinations("JFK", ans, graph, availEdges, tickets.size());
        ans.add("JFK");

        Collections.reverse(ans);
        return ans;
    }
}
