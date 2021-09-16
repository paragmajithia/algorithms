package com.leetcode.geometry;

/**
 * 593. Valid Square
 * Medium
 *
 * 548
 *
 * 655
 *
 * Add to List
 *
 * Share
 * Given the coordinates of four points in 2D space p1, p2, p3 and p4, return true if the four points construct a square.
 *
 * The coordinate of a point pi is represented as [xi, yi]. The input is not given in any order.
 *
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 *
 *
 *
 * Example 1:
 *
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * Output: true
 * Example 2:
 *
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
 * Output: false
 * Example 3:
 *
 * Input: p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
 * Output: true
 *
 *
 * Constraints:
 *
 * p1.length == p2.length == p3.length == p4.length == 2
 * -104 <= xi, yi <= 104
 */

import java.util.*;

public class ValidSquare {

    // a square is valid if all its (4) sides are equal and (2) diagnoals are of equal length
    // Diagonal should be compared as we should not have rhombus
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Set<Integer> resp = new HashSet<>();
        resp.addAll(Arrays.asList(
                dist(p1, p2),
                dist(p1, p3),
                dist(p1, p4),
                dist(p2, p3),
                dist(p2, p4),
                dist(p3, p4)
        ));
        if (resp.size() == 2 && !resp.contains(0)) {
            return true;
        }
        return false;
    }

    public int dist(int[] p1, int[] p2) {
        // distance between 2 points
        // Math.sqrt((x2 -x1)^2 + (y2-y1)^2)
        // We just return square to avoid floating point numbers
        return (p1[0]-p2[0])*(p1[0]-p2[0]) + (p1[1]-p2[1])*(p1[1]-p2[1]);
    }
}

