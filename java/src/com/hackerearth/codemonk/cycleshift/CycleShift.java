package com.hackerearth.codemonk.cycleshift;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * All test completes witin 1 sec from hacker earth (full score 30)
 */
public class CycleShift {

    // Statis members
    static int noTestcases;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // instance members for input
    int strSize;
    long kthTime;
    String input;
    // Calculated values
    String maxString;
    int[] prefixTable;
    int firstCycleShift;
    long repeatedShift;

    /**
     * The solution is divided into 2 steps
     * 1. Find the max length of binary string and the cycles to arrive at max string
     *      a. Note -- this is done efficiently by first concatenating the same intput string (ex: ab becomes abab)
     *      b. Then only compare strings starting with 01 (keep default max as input string)
     *      <Dont join substring's in loop in brutforce comparison ever at increases memory and execution time)
     *  2. Find the cycles required to get max string kth time
     *      a. For this just find the number of times the max string repeats within the input string.
     *      b. the number of cycles can then by calculated mathematically
     *      c. use kmp algo (prefix table) to search for max string within the input string
     *      (above will reduce number of string comparison. dont compare string at each start position)
     *
     * @param args
     * @throws IOException
     */
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
    private static long getTotalCycleShift(CycleShift shift) {

        // STEP 1 -- find the count for first cycle shift and max string
        findMaxString(shift);

        // STEP 2 -- Get prefix table for the pattern (KMP algo)
        shift.prefixTable = getPrefixTable(shift.maxString);

        // STEP 2 -- Get the count of cycles required to get max string kth time
        getRepeatedString(shift);
        // System.out.println(("final count of cycles computation complete in ms: " + (end - start) / 1000000L));

        return (shift.firstCycleShift + shift.repeatedShift);
    }

    /*
    Finds position and number of time given string is getting repeated in input string (concatenated)
     */
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

        long time = shift.kthTime - 1;
        long cycleShifts = (time / repPos.size()) * shift.maxString.length();
        long count = time % (repPos.size());
        for (int index = 0; index < count; index++){
            if (index == 0) cycleShifts = cycleShifts + repPos.get(0);
            else {
                cycleShifts = cycleShifts + (repPos.get(index) - repPos.get(index-1));
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

            if (inputTwice.charAt(i-1) == '0' && inputTwice.charAt(i) == '1' && shift.maxString.compareTo(inputTwice.substring(i, i+shift.strSize)) < 0) {
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
