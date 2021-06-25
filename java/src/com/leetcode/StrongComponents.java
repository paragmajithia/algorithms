package com.leetcode;

/*
Given a 2D grid consisting of 1s (land) and 0s (water).  An island is a maximal 4-directionally (horizontal or vertical) connected group of 1s.

The grid is said to be connected if we have exactly one island, otherwise is said disconnected.

In one day, we are allowed to change any single land cell (1) into a water cell (0).

Return the minimum number of days to disconnect the grid.



Example 1:



Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
Output: 2
Explanation: We need at least 2 days to get a disconnected grid.
Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.
Example 2:

Input: grid = [[1,1]]
Output: 2
Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands.
Example 3:

Input: grid = [[1,0,1,0]]
Output: 0
Example 4:

Input: grid = [[1,1,0,1,1],
               [1,1,1,1,1],
               [1,1,0,1,1],
               [1,1,0,1,1]]
Output: 1
Example 5:

Input: grid = [[1,1,0,1,1],
               [1,1,1,1,1],
               [1,1,0,1,1],
               [1,1,1,1,1]]
Output: 2


Constraints:

1 <= grid.length, grid[i].length <= 30
grid[i][j] is 0 or 1.
 */
public class StrongComponents {

    public static void main(String[] args) {
        System.out.println("Expected 2, Program Output: Number of days: " +
                (new StrongComponents()).minDays( new int[][]{{0,1,1,0}, {0,1,1,0}, {0,0,0,0}} ));

        System.out.println("Expected 2, Program Output: Number of days: " +
                (new StrongComponents()).minDays( new int[][]{{1,1}} ));

        System.out.println("Expected 0, Program Output: Number of days: " +
                (new StrongComponents()).minDays( new int[][]{{1,0, 1, 0}} ));

        System.out.println("Expected 1, Program Output: Number of days: " +
                (new StrongComponents()).minDays( new int[][]{{1,1, 0, 1, 1},
                        {1,1, 1, 1, 1},
                        {1,1, 0, 1, 1},
                        {1,1, 0, 1, 1}} ));

        System.out.println("Expected 2, Program Output: Number of days: " +
                (new StrongComponents()).minDays( new int[][]{{1,1, 0, 1, 1},
                        {1,1, 1, 1, 1},
                        {1,1, 0, 1, 1},
                        {1,1, 1, 1, 1}} ));
    }
    public int minDays(int[][] grid) {

        // Count number of islands
        int count = countComponents(grid);

        if (count != 1) {
            return 0;
        }

        return checkSpecialCase(grid);

    }

    // check for 1 condition if there exists row / column which has only 1 cell for transition from previous to next row / column
    public int checkSpecialCase(int[][] grid) {

        // Reset each bit and check
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    int count = countComponents(grid);
                    if (count != 1) {
                        return 1;
                    }
                    grid[i][j] = 1;
                }
            }
        }

        return 2;

    }

    public int countComponents(int[][] grid) {
        int count = 0;
        int[][] visited = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    visited[i][j] = 1;
                    traverse(i, j, grid, visited);
                    count++;
                }
            }
        }
        return count;
    }

    // DFS traversal
    public void traverse(int i, int j, int[][] grid, int[][] visited) {


        // Traverse up
        if (i > 0 && visited[i - 1][j] == 0 && grid[i - 1][j] == 1) {
            visited[i - 1][j] = 1;
            traverse(i - 1, j, grid, visited);
        }

        // Traverse down
        if (i < (grid.length - 1) && visited[i + 1][j] == 0 && grid[i + 1][j] == 1) {
            visited[i + 1][j] = 1;
            traverse(i + 1, j, grid, visited);
        }

        // Traverse left
        if (j > 0 && visited[i][j - 1] == 0 && grid[i][j - 1] == 1) {
            visited[i][j - 1] = 1;
            traverse(i, j - 1, grid, visited);

        }

        // Traverse down
        if (j < (grid[0].length - 1) && visited[i][j + 1] == 0 && grid[i][j + 1] == 1) {
            visited[i][j + 1] = 1;
            traverse(i, j + 1, grid, visited);

        }


    }
}