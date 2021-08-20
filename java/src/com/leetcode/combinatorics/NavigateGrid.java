package com.leetcode.combinatorics;
/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?



Example 1:


Input: m = 3, n = 7
Output: 28
Example 2:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
Example 3:

Input: m = 7, n = 3
Output: 28
Example 4:

Input: m = 3, n = 3
Output: 6


Constraints:

1 <= m, n <= 100
It's guaranteed that the answer will be less than or equal to 2 * 109.
 */
public class NavigateGrid {

    public static void main(String[] args) {
        NavigateGrid grid = new NavigateGrid();
        System.out.println(grid.uniquePaths(3,2));
    }

    // Total moves for m*n grid to reach from 0,0 to m-1,n-1 is (m-1) + (n-1) = m + n -2
    // Ex for 3*4 matrix, 5 moves could be:
    // R1 D1 D2 R2 D3
    // Assume all right ==> R R R R R and you need to select 2 downs from it
    // Hence its combination problems nCk (select k witout repetition from n elements)
    // = (m+n-2)! / ( (n-1)! * ( ((m+n-2)-(n-1))! )
    // = (m+n-2)! / ( (n-1)! * (m-1)!)
    public int uniquePaths(int m, int n) {

        if (m <= 1 || n <= 1) {
            return 1;
        }

        // To avoid overflow ensure to divide in each step
        long ans = 1;
        for (int i = 1; i <= Math.min(m-1, n-1); i++) {
            ans = ans * (m+n-1-i) /i;
        }

        Long longVal = ans;
        return longVal.intValue();

    }
}