package com.leetcode.disjointsets;

import java.util.Arrays;

public class RedundantConnection {
    public static void main(String[] args) {
        // [[1,4],[3,4],[1,3],[1,2],[4,5]]
        int[][] edges = new int[5][2];
        edges[0] = new int[] {1, 4};
        edges[1] = new int[] {3, 4};
        edges[2] = new int[] {1, 3};
        edges[3] = new int[] {1, 2};
        edges[4] = new int[] {4,5};

        RedundantConnection con = new RedundantConnection();
        System.out.println(Arrays.toString(con.findRedundantConnection(edges)));
    }
    public int[] findRedundantConnection(int[][] edges) {

        int[] parent = new int[edges.length + 1];
        int[] rank = new int[edges.length + 1];
        int[] resp = new int[2];

        for (int i = 1; i < parent.length; i ++) {
            parent[i] = i;
        }

        System.out.println("Parent: " +  Arrays.toString(parent));
        for (int[] edge: edges) {
            boolean isSameSet = union(edge[0], edge[1], parent, rank);
            System.out.println("Edge: " + Arrays.toString(edge));
            System.out.println("Parent: " + Arrays.toString(parent));
            if (isSameSet) {
                resp = edge;
            }
        }

        return resp;

    }

    public boolean union(int point1, int point2, int[] parent, int[] rank) {

        int x = findSet(point1, parent);
        int y = findSet(point2, parent);

        if (x == y) {
            return true;
        }

        if (rank[x] > rank[y]) {
            parent[y] = x;
        } else if (rank[y] > rank[x]) {
            parent[x] = y;
        } else {
            parent[y] = x;
            rank[x] = rank[x] + 1;
        }
        return false;

    }

    public int findSet(int point, int[] parent) {

        if (parent[point] == point) {
            return point;
        }
        int newParent = findSet(parent[point], parent);
        parent[point] = newParent;

        return newParent;

    }
}