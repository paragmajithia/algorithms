package com.hackerearth.codemonk.cycleshift;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class CycleShift {

    static int noTestcases;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read number of test cases
        noTestcases = Integer.parseInt(br.readLine());

        // Read each test case and print output -- total cycle shift required
        IntStream.range(0, noTestcases).forEach(testno -> {
            // Read String size and kth time
            try {
                int strSize, kthTime; String input;
                String[] strArray = br.readLine().trim().split("\\s");
                strSize = Integer.parseInt(strArray[0]);
                kthTime = Integer.parseInt(strArray[1]);
                input = br.readLine().trim();

                System.out.println(getTotalCycleShift(strSize, kthTime, input));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    private static int getTotalCycleShift(int strSize, int kthTime, String input) {
        return -1;
    }
}
