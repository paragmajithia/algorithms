package com.leetcode.matrix;

import java.util.*;

/*
Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.


Example 1:


Input: board =
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true
Example 2:

Input: board =
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.


Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
 */
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {

        Map<Integer, Set<Integer>> rowMap = new HashMap<>();
        Map<Integer, Set<Integer>> colMap = new HashMap<>();
        Map<String, Set<Integer>> boxMap = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            for (int j=0; j < 9; j++) {

                if (board[i][j] != '.' && Character.isDigit(board[i][j]) ) {

                    Integer num = Character.getNumericValue(board[i][j]);
                    String boxKey = String.format("%s%s", (int)i/3, (int)j/3);

                    // Validate row, column and box
                    if ( (rowMap.containsKey(i) && rowMap.get(i).contains(num)) ||
                            (colMap.containsKey(j) && colMap.get(j).contains(num)) ||
                            (boxMap.containsKey(boxKey) && boxMap.get(boxKey).contains(num))
                    ) {
                        return false;
                    }

                    // Add num to row, column and box
                    rowMap.computeIfAbsent(i, key -> new HashSet<>()).add(num);
                    colMap.computeIfAbsent(j, key -> new HashSet<>()).add(num);
                    boxMap.computeIfAbsent(boxKey, key -> new HashSet<>()).add(num);

                }


            }
        }


        return true;
    }
}
