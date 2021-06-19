package com.facebook.strings.rotations.minlengthsubstring;

import java.io.*;
import java.util.*;
// Add any extra import statements you may need here


class Main {

    // Add any helper functions you may need here


    int minLengthSubstring(String s, String t) {
        // Write your code here
        // Store count of char in t
        // System.out.println("Input s:" + s + ", t:" + t);
        Map<Character, Integer> target = new HashMap<>();
        Map<Character, Integer> source = new HashMap<>();
        Set<Character> unmatched = new HashSet<>();
        for (int i = 0; i < t.length(); i ++){
            int count = target.getOrDefault(t.charAt(i), 0);
            target.put(t.charAt(i), count + 1);
            unmatched.add(t.charAt(i));
        }

        // Loop till we get first window
        int startPos = -1; int index = 0;
        while (index < s.length() && !unmatched.isEmpty()) {
            // Match found
            if (target.containsKey(s.charAt(index))) {
                if (startPos == -1) {
                    startPos = index;
                }
                int count = source.getOrDefault(s.charAt(index), 0);
                source.put(s.charAt(index), count + 1);
                if (source.get(s.charAt(index)) == target.get(s.charAt(index))) {
                    unmatched.remove(s.charAt(index));
                }
            }
            index++;
        }
        // System.out.println("Source: " + source + ", first startPos:" + startPos);
        // System.out.println("Target: " + target);
        if (!unmatched.isEmpty()) {
            return -1;
        }

        // Moving window
        int min = index - startPos;
        while (index < s.length()) {
            if (!target.containsKey(s.charAt(index))) {
                index++;
                continue;
            }
            // Match found -- increment count
            source.put(s.charAt(index), source.get(s.charAt(index)) + 1);

            // move left window as much as we can
            while (!target.containsKey(s.charAt(startPos)) || target.get(s.charAt(startPos)) <= (source.get(s.charAt(startPos)) - 1)) {
                if (!target.containsKey(s.charAt(startPos))) {
                    startPos = startPos + 1;
                    continue;
                }
                source.put(s.charAt(startPos), source.get(s.charAt(startPos)) - 1);
                startPos = startPos + 1;
            }
            // System.out.println("Source: " + source + ", index: " + index + ", startPos: " + startPos);

            // Check window size
            min = Math.min(min, index - startPos + 1);
            index++;
        }

        return min;
    }











    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;
    void check(int expected, int output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printInteger(expected);
            System.out.print(" Your output: ");
            printInteger(output);
            System.out.println();
        }
        test_case_number++;
    }
    void printInteger(int n) {
        System.out.print("[" + n + "]");
    }
    public void run() throws IOException {
        String s_1 = "dcbefebce";
        String t_1 = "fd";
        int expected_1 = 5;
        int output_1 = minLengthSubstring(s_1, t_1);
        check(expected_1, output_1);

        String s_2 = "bfbeadbcbcbfeaaeefcddcccbbbfaaafdbebedddf";
        String t_2 = "cbccfafebccdccebdd";
        int expected_2 = -1;
        int output_2 = minLengthSubstring(s_2, t_2);
        check(expected_2, output_2);

        // Add your own test cases here
        String s_3 = "dcbefedce";
        String t_3 = "fd";
        int expected_3 = 3;
        int output_3 = minLengthSubstring(s_3, t_3);
        check(expected_3, output_3);

        String s_4 = "dcbefedceabcfabc";
        String t_4 = "fd";
        int expected_4 = 3;
        int output_4 = minLengthSubstring(s_4, t_4);
        check(expected_4, output_4);

    }
    public static void main(String[] args) throws IOException {
        new Main().run();
    }
}
