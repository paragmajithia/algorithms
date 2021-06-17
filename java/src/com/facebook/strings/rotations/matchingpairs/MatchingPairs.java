package com.facebook.strings.rotations.matchingpairs;

import java.io.*;
import java.util.*;
// Add any extra import statements you may need here

import java.io.*;
import java.util.*;
// Add any extra import statements you may need here


class MatchingPairs {

    // Add any helper functions you may need here

    int countUnMatched(String s, String t, Map<Character, List<Integer>> charMapT, Set<Character> matchedS, Set<Integer> unmatchedS) {
        // Check all unmatched character to see if it can be swapped to get exact match

        int resp = 0;
        for (int s_index: unmatchedS) {
            char unmatchedCharAtS = s.charAt(s_index);
            char unmatchedCharAtT = t.charAt(s_index);
            if (charMapT.containsKey(unmatchedCharAtS)) {
                resp = 1;
                for (int j_index: charMapT.get(unmatchedCharAtS) ) {
                    if(s.charAt(j_index) == unmatchedCharAtT) {
                        return 2;
                    }
                }
            }
        }
        return resp;
    }

    int matchingPairs(String s, String t) {
        // Write your code here
        int countMatch = 0;
        Set<Integer> unmatchedS = new HashSet();
        Map<Character, List<Integer>> charMapT = new HashMap();
        Set<Character> matchedS = new HashSet();

        // Lop each character
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == t.charAt(i)) {
                countMatch++;
                matchedS.add(s.charAt(i));
            } else {
                charMapT.putIfAbsent(t.charAt(i),new ArrayList<Integer>());
                charMapT.get(t.charAt(i)).add(i);
                unmatchedS.add(i);
            }
        }
        // System.out.println("S:" + s + ", T:" + t);
        // System.out.println("Unmatched S:" + unmatchedS);
        // System.out.println("Matched S:" + matchedS);
        // System.out.println("Unmatched T:" + charMapT);
        // 3 cases
        // THere are more than 1 character that are unmatched
        if (countMatch <= s.length() - 2) {
            return countMatch + countUnMatched(s,t, charMapT, matchedS, unmatchedS);
        } else if (countMatch == s.length() - 1) {
            // Exactly one char is unmatched
            for (int s_index: unmatchedS) {
                if (matchedS.contains(s.charAt(s_index))) {
                    return countMatch;
                } else {
                    return countMatch - 1;
                }
            }
        } else if (countMatch == s.length()) {
            // All characters are matched
            if (matchedS.size() == s.length()) {
                return countMatch - 2;
            } else {
                return countMatch;
            }

        }

        return countMatch;
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
    public void run() {
        String s_1 = "abcde";
        String t_1 = "adcbe";
        int expected_1 = 5;
        int output_1 = matchingPairs(s_1, t_1);
        check(expected_1, output_1);

        String s_2 = "abcd";
        String t_2 = "abcd";
        int expected_2 = 2;
        int output_2 = matchingPairs(s_2, t_2);
        check(expected_2, output_2);

        // Add your own test cases here
        String s_3 = "abcdc";
        String t_3 = "abcde";
        int expected_3 = 4;
        int output_3 = matchingPairs(s_3, t_3);
        check(expected_3, output_3);

        String s_4 = "abcdf";
        String t_4 = "abcde";
        int expected_4 = 3;
        int output_4 = matchingPairs(s_4, t_4);
        check(expected_4, output_4);

        String s_5 = "abcc";
        String t_5 = "abcc";
        int expected_5 = 4;
        int output_5 = matchingPairs(s_5, t_5);
        check(expected_5, output_5);

    }
    public static void main(String[] args) {
        new MatchingPairs().run();
    }
}