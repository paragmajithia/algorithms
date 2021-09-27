package com.leetcode.binarytree;

import java.util.*;

/**
 * 331. Verify Preorder Serialization of a Binary Tree
 * Medium
 *
 * 1451
 *
 * 74
 *
 * Add to List
 *
 * Share
 * One way to serialize a binary tree is to use preorder traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as '#'.
 *
 *
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where '#' represents a null node.
 *
 * Given a string of comma-separated values preorder, return true if it is a correct preorder traversal serialization of a binary tree.
 *
 * It is guaranteed that each comma-separated value in the string must be either an integer or a character '#' representing null pointer.
 *
 * You may assume that the input format is always valid.
 *
 * For example, it could never contain two consecutive commas, such as "1,,3".
 * Note: You are not allowed to reconstruct the tree.
 *
 *
 *
 * Example 1:
 *
 * Input: preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * Output: true
 * Example 2:
 *
 * Input: preorder = "1,#"
 * Output: false
 * Example 3:
 *
 * Input: preorder = "9,#,#,1"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= preorder.length <= 104
 * preorder consist of integers in the range [0, 100] and '#' separated by commas ','.
 */
public class VerifyPreOrderSerialization {
    public static void main(String[] args) {
        VerifyPreOrderSerialization main = new VerifyPreOrderSerialization();
        // System.out.println("answer: " + main.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        System.out.println("answer: " + main.isValidSerialization( "9,#,#,1,#,#,3,#,#"));

    }
    public boolean isValidSerialization(String preorder) {

        String[] currArr = preorder.split(",");
        List<String> currList = new ArrayList(Arrays.asList(currArr));
        List<String> newList = new ArrayList();

        boolean foundHash = true;
        while (foundHash) {

            System.out.println("CurrList: " + currList);
            System.out.println("NewList: " + newList);
            System.out.println("====");
            foundHash = false;
            int i = 0;
            while (i < currList.size()) {
                if (i < (currList.size() - 2) &&
                        !currList.get(i).equals("#") &&
                        currList.get(i+1).equals("#") &&
                        currList.get(i+2).equals("#")){
                    newList.add("#");
                    i = i + 3;
                    foundHash = true;
                } else {
                    newList.add(currList.get(i));
                    i = i + 1;
                }
            }
            currList = newList;
            newList = new ArrayList();
        }

        if (currList.size() == 1 && currList.get(0).equals("#")) {
            return true;
        }
        return false;
    }

    /**
     * ANOTHER SOLUTION USING STACK
     * @param preorder
     * @return
     */
    public boolean isValidSerializationusingStack(String preorder) {
        // using a stack, scan left to right
        // case 1: we see a number, just push it to the stack
        // case 2: we see #, check if the top of stack is also #
        // if so, pop #, pop the number in a while loop, until top of stack is not #
        // if not, push it to stack
        // in the end, check if stack size is 1, and stack top is #
        if (preorder == null) {
            return false;
        }
        Stack<String> st = new Stack<>();
        String[] strs = preorder.split(",");
        for (int pos = 0; pos < strs.length; pos++) {
            String curr = strs[pos];
            while (curr.equals("#") && !st.isEmpty() && st.peek().equals(curr)) {
                st.pop();
                if (st.isEmpty()) {
                    return false;
                }
                st.pop();
            }
            st.push(curr);
        }
        return st.size() == 1 && st.peek().equals("#");
    }
}