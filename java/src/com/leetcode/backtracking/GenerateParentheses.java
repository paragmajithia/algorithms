package com.leetcode.backtracking;

import java.util.*;
/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.



Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]


Constraints:

1 <= n <= 8
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        GenerateParentheses paren = new GenerateParentheses();
        System.out.println(paren.generateParenthesis(3));
    }

    /** 1 ()
     2 ()(), (())
     3 ()()(), (())(), ()(()),((())),((()))
     Loop:
     ((())))
     ((()))
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        StringBuilder sb = new StringBuilder();
        appendParanthesis(sb, 0,0, n, ans);
        return ans;

    }

    private void appendParanthesis(StringBuilder sb, int open, int close, int max, List<String> ans) {

        // Answer valid
        if (sb.length() >= 2*max) {
            ans.add(sb.toString());
            return;
        }

        if (open < max) {
            sb.append("(");
            appendParanthesis(sb, open+1, close, max, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (open > close) {
            sb.append(")");
            appendParanthesis(sb, open, close+1, max, ans);
            sb.deleteCharAt(sb.length() - 1);
        }

    }
}