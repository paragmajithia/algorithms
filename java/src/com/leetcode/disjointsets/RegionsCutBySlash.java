package com.leetcode.disjointsets;

import java.util.*;

/*
An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '.
These characters divide the square into contiguous regions.
Given the grid grid represented as a string array, return the number of regions.

Note that backslash characters are escaped, so a '\' is represented as '\\'.



Example 1:


Input: grid = [" /","/ "]
Output: 2
Example 2:


Input: grid = [" /","  "]
Output: 1
Example 3:


Input: grid = ["\\/","/\\"]
Output: 4
Explanation: (Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.)
Example 4:


Input: grid = ["/\\","\\/"]
Output: 5
Explanation: (Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.)
Example 5:


Input: grid = ["//","/ "]
Output: 3


Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 30
grid[i][j] is either '/', '\', or ' '.
 */

public class RegionsCutBySlash {

    public static void main(String[] args) {
        RegionsCutBySlash slash = new RegionsCutBySlash();
        String[] grid = new String[] {" /", " /"};
        System.out.println(slash.regionsBySlashes(grid));
    }

    public int findSet(int x, int[] parent, int[] rank) {

        if (parent[x] == x) {
            return x;
        }

        int setNum = findSet(parent[x], parent, rank);
        parent[x] = setNum;

        return setNum;

    }

    public void union(int x, int y, int[] parent, int[] rank) {

        int xNum = findSet(x, parent, rank);
        int yNum = findSet(y, parent, rank);

        if (xNum == yNum) {
            return;
        }

        if (rank[xNum] > rank[yNum]) {
            parent[yNum] = xNum;
        } else if (rank[xNum] < rank[yNum]) {
            parent[xNum] = yNum;
        } else {
            parent[yNum] = xNum;
            rank[xNum]++;
        }

    }

    public int regionsBySlashes(String[] grid) {

        int points = grid.length+1;
        int totalPoints = points * points;
        int[] parent = new int[totalPoints];
        int[] rank =  new int[totalPoints];
        // Set all parents to itself
        for (int i = 0; i < totalPoints; i++) {
            parent[i] = i;
        }

        // Add all points
        for (int i = 0; i < points; i++) {
            for (int j=0; j< points; j++) {
                if (i == 0 || j == 0 || i == (points-1) || j == (points - 1)) {
                    int pointNum = (i*points) + j;
                    // Union with 0th set
                    if (pointNum != 0) {
                        union(0, pointNum, parent, rank);
                    }
                }
            }
        }

        System.out.println("Parents: " + Arrays.toString(parent));

        int regions = 1;

        // Parse the input
        for (int i = 0; i < grid.length; i++) {
            for (int j=0; j < grid[i].length(); j++) {
                if (grid[i].charAt(j) == '/') {
                    // Connect i,j+1 & i+1,j
                    int first = (i*points) + j + 1;
                    int second = (i+1)*points + j;

                    int firstNum = findSet(first, parent, rank);
                    int secondNum = findSet(second, parent, rank);
                    if (firstNum == secondNum) {
                        regions++;
                    } else {
                        union(first,second, parent, rank);
                    }


                } else if (grid[i].charAt(j) == '\\') {
                    // Connect i,j & i+1,j+1
                    int first = (i*points) + j;
                    int second = (i+1)*points + j + 1;

                    int firstNum = findSet(first, parent, rank);
                    int secondNum = findSet(second, parent, rank);
                    if (firstNum == secondNum) {
                        regions++;
                    } else {
                        union(first,second, parent, rank);
                    }
                }

            }
        }

        return regions;

    }
}