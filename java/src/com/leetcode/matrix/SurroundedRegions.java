package com.leetcode.matrix;

/**
 * 130. Surrounded Regions
 * Medium
 *
 * 3455
 *
 * 880
 *
 * Add to List
 *
 * Share
 * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * Explanation: Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
 * Example 2:
 *
 * Input: board = [["X"]]
 * Output: [["X"]]
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is 'X' or 'O'.
 */
public class SurroundedRegions {

    public void solve(char[][] board) {

        // Trick -- Exclusion principle
        // Exclude all 0's that cant be converted to X
        // This can be done by marking those border 0's as *
        // Mark all those 0's as * recursively that are neghbors of bordered 0's
        // At the end revert those * to 0's and other pending 0's to X

        // Loop through left and right column
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            markBoundary(board, i, 0, m, n); // Let col
            markBoundary(board, i, n-1, m, n); // Right col
        }

        // Loop through bottom and top row
        for (int i = 0; i < n; i++) {
            markBoundary(board, 0, i, m, n); // Top row
            markBoundary(board, m-1, i, m, n); // bottom row
        }

        // Post processing
        // Convert all 0's to X and '*' to 0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '*') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }

    }

    public void markBoundary(char[][] board, int i, int j, int m, int n) {

        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        if (board[i][j] == 'O') {
            board[i][j] = '*';

            // Recurse neighbors
            markBoundary(board, i-1, j, m, n);
            markBoundary(board, i+1, j, m, n);
            markBoundary(board, i, j-1, m, n);
            markBoundary(board, i, j+1, m, n);
        }
    }
}
