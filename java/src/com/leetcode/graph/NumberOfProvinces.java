package com.leetcode.graph;

/**
 * 547. Number of Provinces
  * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 * Example 2:
 *
 *
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] is 1 or 0.
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */

import java.util.*;

public class NumberOfProvinces {

    public static void main(String[] args) {

        NumberOfProvinces main = new NumberOfProvinces();
        System.out.println("Answer: " + main.findCircleNum(new int[][] {{1,1,1}, {1,1,1}, {1,1,1}}));

    }

    public int findCircleNum(int[][] isConnected) {

        int n = isConnected.length;
        int[] parent = new int[n];
        int[] rank = new int[n];
        int province = n;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (isConnected[i][j] == 1) {

                    System.out.println("Set before union of " + i + " & " + j + ": " + Arrays.toString(parent) + ",Province : " + province);
                    province = union(i, j, parent, rank, province);
                    System.out.println("Set after union of " + i + " & " + j + ": " + Arrays.toString(parent) + ",Province : " + province);
                }
            }
        }

        return province;
    }

    public int union(int pt1, int pt2, int[] parent, int[] rank, int province) {

        int set1 = find(pt1,parent);
        int set2 = find(pt2,parent);

        if (set1 == set2) {
            return province;
        }

        if (rank[set1] > rank[set2]) {
            parent[set2] = set1;
        } else if (rank[set2] > rank[set1]) {
            parent[set1] = set2;
        } else {
            parent[set1] = set2;
            rank[set2] = rank[set2] + 1;
        }
        return province -1;

    }

    public int find(int pt1, int[] parent) {
        int set = parent[pt1];
        if (parent[pt1] != pt1) {
            set = find(set, parent);
            parent[pt1] = set;
        }
        return set;
    }
}
