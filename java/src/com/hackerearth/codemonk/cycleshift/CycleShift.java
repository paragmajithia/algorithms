package com.hackerearth.codemonk.cycleshift;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CycleShift {

    static int noTestcases;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // instance members for input
    int strSize, kthTime;
    String input;
    // Calculated values
    String maxString;
    int[] prefixTable;
    int firstCycleShift;
    int repeatedShift;

    public static void main(String[] args) throws IOException {

        // Read number of test cases
        noTestcases = Integer.parseInt(br.readLine());
        List<CycleShift> testInput = new ArrayList<>();

        // Read each test case and store test input data
        IntStream.range(0, noTestcases).forEach(testno -> {
            // Read String size and kth time
            try {
                CycleShift shift = new CycleShift();
                String[] strArray = br.readLine().trim().split("\\s");
                shift.strSize = Integer.parseInt(strArray[0]);
                shift.kthTime = Integer.parseInt(strArray[1]);
                shift.input = br.readLine().trim();
                testInput.add(shift);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Calculate cycle shift for each test case and print result
        IntStream.range(0, noTestcases).forEach(testno -> {
            System.out.println(getTotalCycleShift(testInput.get(testno)));
        });
    }

    // Main logic
    private static int getTotalCycleShift(CycleShift shift) {
        findMaxString(shift);
        shift.prefixTable = getPrefixTable(shift.maxString);
        getRepeatedString(shift);
        return (shift.firstCycleShift + shift.repeatedShift);
    }

    private static void getRepeatedString(CycleShift shift) {
        String maxTwice = String.format("%s%s", shift.maxString, shift.maxString);
        List<Integer> repPos = new ArrayList<>();
        int i = 1, j = 0;
        while (i < maxTwice.length()) {
            // Increment if match is found
            if (maxTwice.charAt(i) == maxTwice.charAt(j)) {
                i++;
                j++;
            }

            // If whole string is matched
            if (j == shift.maxString.length()) {
                repPos.add(i-j);
                j = shift.prefixTable[j-1];
            } else if (i < maxTwice.length() && maxTwice.charAt(i) != maxTwice.charAt(j)) { // Mismatch after j matches
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = shift.prefixTable[j - 1];
                else
                    i = i + 1;
            }
        }

        int time = shift.kthTime - 1;
        int cycleShifts = (time / repPos.size()) * shift.maxString.length();
        int count = time % (repPos.size());
        for (int index = 0; index < count; index++){
            if (index == 0) cycleShifts = cycleShifts + repPos.get(0);
            else {
                cycleShifts = cycleShifts + (repPos.get(i) - repPos.get(i-1));
            }
        }
        shift.repeatedShift = cycleShifts;

    }

    /*
    find max string after cycle shift
     */
    private static void findMaxString(CycleShift shift) {

        shift.maxString = shift.input;
        shift.firstCycleShift = 0;

        String inputTwice = String.format("%s%s", shift.input, shift.input);

        // Find next 0 as max number would always start with 1
        for (int i = 1; i < shift.strSize; i++) {            // find next sequence of 01

            if (shift.maxString.compareTo(inputTwice.substring(i, i+shift.strSize)) < 0) {
                shift.maxString = inputTwice.substring(i, i+shift.strSize);
                shift.firstCycleShift = i;
            }
        }
    }

    private static int[] getPrefixTable(String pattern) {
        if (pattern == null || pattern.isEmpty()) return null;

        int[] prefixTable = new int[pattern.length()];
        // First element is always 0
        prefixTable[0] = 0;
        // J is the previous longest prefix suffix
        int i = 1, j = 0;

        while(i < pattern.length() ){
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                prefixTable[i] = j;
                i++;
            } else { // Char at pattern doesnt match

                // This is tricky. Consider the example.
                // AAACAAAA and i = 7.
                // 01201233
                if (j!= 0) {
                    // As 4th element (j=3) did not match, compare 3rd element prefix[2] in next loop
                    j= prefixTable[j-1];
                } else {
                    prefixTable[i] = j;
                    i++;
                }
            }

        }
        return prefixTable;
    }
}
