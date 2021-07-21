package com.trafigura.temp;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

class Solution {
    public static void main(String[] args) {
        System.out.println((new Solution()).buildTree(new int[]{9,3,15,20,7}, new int[] {9,15,7,20,3}));
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {

        // Base case
        if (postorder.length <= 0) {
            return null;
        }

        // Center node
        TreeNode center = new TreeNode(postorder[postorder.length -1]);

        if (postorder.length == 1) {
            return center;
        }

        return split(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode split(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd) {

        if(inStart > inEnd) return null;

        // Center node
        TreeNode center = new TreeNode(postorder[postEnd]);

        int index = inStart;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == center.val) {
                index = i;
                break;
            }
        }

        int leftsize = index - inStart;
        int rightsize = inEnd - index;

        center.left = split(inorder, postorder, inStart, index-1, postStart, postStart+ leftsize-1);
        center.right = split(inorder, postorder, index+1, inEnd, postEnd- rightsize, postEnd-1);
        return center;

    }
}