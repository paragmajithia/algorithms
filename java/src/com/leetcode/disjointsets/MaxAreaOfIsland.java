package com.leetcode.disjointsets;

/**
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 *
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1.
 */

import java.util.*;
public class MaxAreaOfIsland {

    public int maxAreaOfIsland(int[][] grid) {

        int M = grid.length;
        int N = grid[0].length;

        int[] parent = new int[M*N];
        int[] size = new int[M*N];
        int[] rank = new int[M*N];
        Arrays.fill(size, 1);
        // Make the set
        makeSet(grid, parent, M,N);

        int max = 0;

        // Loop through the matrix
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N ; j ++) {

                if (grid[i][j] == 1) {
                    int num = i*N + j;
                    max = Math.max(max, size[num]);
                    // Check down
                    if (i+1 < M && grid[i+1][j] == 1) {
                        int num2 = (i+1)*N + j;
                        max = Math.max(max, union(num, num2, parent, size, rank));
                    }

                    // Check right
                    if (j+1 < N && grid[i][j+1] == 1) {
                        int num2 = i*N + (j+1);
                        max = Math.max(max, union(num, num2, parent, size, rank));
                    }
                }
            }
        }

        return max;

    }

    public void makeSet(int[][] grid, int[] parent, int M, int N) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N ; j ++) {
                int num = i*N + j;
                parent[num] = num;
            }
        }
    }

    public int union(int p1, int p2, int[] parent, int[] size, int[] rank) {

        int set1 = find(p1, parent);
        int set2 = find(p2, parent);
        if (set1 == set2) {
            return size[set2];
        }

        if (rank[set1] > rank[set2]) {
            parent[set2] = set1;
            size[set1] = size[set1] + size[set2];
            return size[set1];
        } else if (rank[set1] < rank[set2]) {
            parent[set1] = set2;
            size[set2] = size[set1] + size[set2];
            return size[set2];
        } else {
            parent[set1] = set2;
            size[set2] = size[set1] + size[set2];
            rank[set2] = rank[set2] + 1;
            return size[set2];
        }

    }

    public int find(int p1, int[] parent) {
        int set = parent[p1];
        if (set != p1) {
            set = find(set, parent);
            parent[p1] = set;
        }
        return set;

    }
}