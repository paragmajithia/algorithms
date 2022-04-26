package com.leetcode.combinatorics;

/**
 * 96. Unique Binary Search Trees
 *
 * Share
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: 5
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 19
 */

/**
 In this problem we are asked to get number of trees and not necceseraly to return all trees, only number. Here we can use the idea of dynamic programming, let dp[n] be the number of unique Binary Search Trees with n nodes. How can we evaluate them: we need to choose number of nodes in the left subtree and number of nodes in the right subtree, for example n=5, then we have options:

 left subtree has 0 nodes, root = 1, and right subtree has 4 nodes, number of options f[0]*f[4]
 left subtree has 1 nodes, root = 2, and right subtree has 3 nodes, number of options f[1]*f[3]
 left subtree has 2 nodes, root = 3, and right subtree has 2 nodes, number of options f[2]*f[2]
 left subtree has 3 nodes, root = 4, and right subtree has 1 nodes, number of options f[3]*f[1]
 left subtree has 4 nodes, root = 5, and right subtree has 0 nodes, number of options f[4]*f[0]
 So, in total f[5] = f[0]*f[4] + f[1]*f[3] + f[2]*f[2] + f[3]*f[1] + f[4]*f[0], and in general:
 f[n] = f[0]*f[n-1] + f[1]*f[n-2] + ... + f[n-2]*f[1] + f[n-1]*f[0].

 We can solve this in classical dynamic programming way with O(n^2) complexity. However we can recognize in this formula Catalan Numbers: https://en.wikipedia.org/wiki/Catalan_number and there is direct formula to evaluate them:
 f[n] = (2n)!/(n! * n! * (n+1)).

 Complexity: time complexity is O(n) to evaluate all factorials, space complexity is O(1).
 */

/**
 * http://www.geometer.org/mathcircles/catalan.pdf
 */

class UniqueBST {

    public static void main(String[] args) {
        UniqueBST obj = new UniqueBST();
        obj.numTrees(3);
    }

    public int numTrees(int n) {
        int[] dp = new int[n+1];
        return dpNumtrees(n, dp);
    }

    public int dpNumtrees(int n, int[] dp) {

        if (n == 0 || n == 1) {
            return 1;
        }

        if (dp[n] != 0) return dp[n];

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans +=  dpNumtrees(i, dp) * dpNumtrees(n-i-1, dp);
        }
        dp[n] = ans;
        return ans;

    }
}
