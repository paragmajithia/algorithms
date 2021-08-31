package com.leetcode.bitmask;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MatchSticksToSquare {
    public static void main(String[] args) {
        MatchSticksToSquare square = new MatchSticksToSquare();
        //System.out.println("Answer: " + square.makesquare(new int[] {1,1,2,2,2}));
        //System.out.println("=====================================");
        //System.out.println("Answer: " + square.makesquare(new int[] {3,3,3,3,4}));
        //System.out.println("=====================================");
        System.out.println("Answer: " + square.makesquare(new int[] {5,5,5,5,16,4,4,4,4,4,3,3,3,3,4}));
        // System.out.println("Answer: " + square.makesquare(new int[] {16,4,4,4,4,4,18,18}));

    }
    public boolean makesquare(int[] matchsticks) {

        // Get square side length
        int perimeter = 0;
        for (int i = 0; i < matchsticks.length; i++) {
            perimeter += matchsticks[i];
        }
        int side = perimeter /4;

        // Return if sides cant be formed
        if (side * 4 != perimeter) {
            return false;
        }

        // Pick the match stick one by one and see if sides can be formed
        // Compare max 3 sides
        // Revert if selection is not correct
        int usedMask = 0; // No match stick used

        int[] nums = Arrays.stream(matchsticks).boxed().sorted(Collections.reverseOrder()).mapToInt(val -> val).toArray();
        System.out.println("Match sticks: " + Arrays.toString(nums));
        System.out.println("Perimeter: " + perimeter + ", side: " + side + ", mask: " + Integer.toString(usedMask, 2));
        Map<Pair<Integer, Integer>, Boolean> mem = new HashMap<>();
        return recurse(nums, usedMask, 0, 0, side, mem);

    }

    public boolean recurse(int[] matchsticks, int usedMask, int sidesCompleted, int currSideSum, int expectedSideSum, Map<Pair<Integer, Integer>, Boolean> mem) {

        System.out.println("Curr Side Sum: " + currSideSum + ", side completed: " + sidesCompleted + ", mask: " + Integer.toString(usedMask, 2));

        // Square can be formed
        if (sidesCompleted == 3) {
            return true;
        }

        Pair<Integer, Integer> memKey = new Pair<>(usedMask, sidesCompleted);
        if (mem.containsKey(memKey)) {
            System.out.println("Mem hit: Key: " + memKey + ", Val: " + mem.get(memKey));
            return mem.get(memKey);
        }

        boolean ans = false;
        for (int i =0; i < matchsticks.length; i++) {
            // if matchstick is not used and size is less than expected
            if (((usedMask & (1 << i)) == 0) && matchsticks[i] <= (expectedSideSum - currSideSum)) {
                int newSideSum = currSideSum + matchsticks[i];
                int newsidesCompleted = sidesCompleted;
                if (newSideSum == expectedSideSum) {
                    newsidesCompleted = newsidesCompleted+1;
                    newSideSum = 0;
                };
                boolean resp = recurse(matchsticks, (usedMask ^ (1 << i)),  newsidesCompleted, newSideSum, expectedSideSum, mem) ;
                if (resp) {
                    ans = true;
                    break;
                }
            }
        }


        // Default false
        mem.put(memKey, ans);
        return ans;

    }
}