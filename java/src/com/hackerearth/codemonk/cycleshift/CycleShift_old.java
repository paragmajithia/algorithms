package com.hackerearth.codemonk.cycleshift;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CycleShift_old {

    static int noTestcases;

    // instance members for input
    int strSize, kthTime;
    String input;
    // Calculated values
    String maxString;
    int firstCycleShift;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read number of test cases
        noTestcases = Integer.parseInt(br.readLine());
        List<CycleShift_old> testInput = new ArrayList<>();

        // Read each test case and store test input data
        IntStream.range(0, noTestcases).forEach(testno -> {
            // Read String size and kth time
            try {
                CycleShift_old shift = new CycleShift_old();
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
    private static int getTotalCycleShift(CycleShift_old shift) {
        findMaxString(shift);
        return -1;
    }

    /*
    find max string after cycle shift
     */
    private static void findMaxString(CycleShift_old shift) {

        shift.maxString = shift.input;
        shift.firstCycleShift = 0;
        int maxNumber = Integer.parseInt(shift.maxString);

        // Find next 0 as max number would always start with 1
        for (int i = 1; i < shift.strSize; i++) {
            // find next sequence of 01
            if ('0' == shift.input.charAt(i-1) && '1' == shift.input.charAt(i)) {

                String newStrToTest = shift.input.substring(i) + shift.input.substring(0, i);
                if (Integer.parseInt(newStrToTest) > maxNumber) {
                    shift.maxString = newStrToTest;
                    maxNumber = Integer.parseInt(shift.maxString);
                    shift.firstCycleShift = i;
                }
            }
        }
    }
}
