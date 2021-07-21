package com.facebook.puzzles;

public class RotaryLock {
    public static void main(String[] args) {
        RotaryLock lock = new RotaryLock();

        System.out.print("Expected 11, Actual: ");
        System.out.println(lock.getMinCodeEntryTime(10, 4, new int[] {9,4,4,8}));

        System.out.print("Expected 3, Actual: ");
        System.out.println(lock.getMinCodeEntryTime(3, 7, new int[] {3,3,1,2,2,2,2}));

        System.out.print("Expected 8, Actual: ");
        System.out.println(lock.getMinCodeEntryTime(3, 9, new int[] {1,2,3,1,2,3,1,2,3}));

        System.out.print("Expected 59, Actual: ");
        System.out.println(lock.getMinCodeEntryTime(100, 3, new int[] {40,50,60}));

        System.out.print("Expected 50, Actual: ");
        System.out.println(lock.getMinCodeEntryTime(100, 3, new int[] {51,51,51}));

        System.out.print("Expected 50, Actual: ");
        System.out.println(lock.getMinCodeEntryTime(50000000, 3, new int[] {51,51,51}));

        System.out.print("Expected 12, Actual: ");
        System.out.println(lock.getMinCodeEntryTime(10, 4, new int[] {8, 2, 1, 5}));
    }
    public long getMinCodeEntryTime(int N, int M, int[] C) {
        // Write your code here
        int currPos = 1;
        int minSeconds = 0;
        for (int i = 0; i < M; i++) {
            if (C[i] == currPos) {
                continue;
            }
            int diff = Math.abs(currPos - C[i]);
            int diffRev = N - diff;
            minSeconds += Math.min(diff, diffRev);
            currPos = C[i];
        }

        return minSeconds;
    }
}
