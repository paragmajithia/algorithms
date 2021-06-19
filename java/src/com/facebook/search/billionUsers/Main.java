package com.facebook.search.billionUsers;

import java.io.*;
import java.util.*;
// Add any extra import statements you may need here


class Main {

    // Add any helper functions you may need here


    int getBillionUsersDay(float[] growthRates) {
        // Write your code here
        // System.out.println("Growth: " + Arrays.toString(growthRates));
        float minG = 2;
        float maxG = 1;
        for (int i=0; i < growthRates.length; i++) {
            minG = growthRates[i] < minG ? growthRates[i]: minG;
            maxG = growthRates[i] > maxG ? growthRates[i]: maxG;
        }
        //System.out.println("MinG: " + minG + "MaxG: " + maxG);

        // Get Min & Max days -- assume all mingrowth to get maxdays and all max growth to get min days
        // sumAtAnyDay = N * (g ^ t)
        // 1B = N * (g ^ t)
        // 1B/N = g ^ t
        // log (1B/N) = t log g
        // tMax = 1og(1B/N) / log gMin
        // tMin = log(1B/N) / log gMax
        int minDay = (int) Math.floor((Math.log(1000000000/growthRates.length) / Math.log(maxG)));
        int maxDay = (int) Math.ceil((Math.log(1000000000/growthRates.length) / Math.log(minG)));

        double[] curr = new double[growthRates.length];
        int low = minDay; int high = maxDay; int mid = 0;
        // System.out.println( "Min day: " + minDay + ",Max day: " + maxDay);
        while (low <= high) {
            mid = low + (high-low) / 2;
            float sum = 0;
            for (int i=0; i < growthRates.length; i++) {
                sum += Math.pow(growthRates[i], mid);
            }
            // System.out.println("Sum at day: " + mid + " is:" + sum + ", low:" + low + ",high:" + high);
            if (low == high && sum < 1000000000) {
                return ++low;
            } else if (low == high) {
                return low;
            } else if (sum < 1000000000) {
                low = mid + 1;
            } else if (sum > 1000000000) {
                high = mid - 1;
            }
        }
        return mid;
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
        float[] test_1 = {1.1f, 1.2f, 1.3f};
        int expected_1 = 79;
        int output_1 = getBillionUsersDay(test_1);
        check(expected_1, output_1);

        float[] test_2 = {1.01f, 1.02f};
        int expected_2 = 1047;
        int output_2 = getBillionUsersDay(test_2);
        check(expected_2, output_2);


        // Add your own test cases here
        float[] test_3 = {1.5f, 1.5f};
        int expected_3 = 50;
        int output_3 = getBillionUsersDay(test_3);
        check(expected_3, output_3);

        float[] test_4 = {1.9f, 1.9f};
        int expected_4 = 32;
        int output_4 = getBillionUsersDay(test_4);
        check(expected_4, output_4);

    }
    public static void main(String[] args) {
        new Main().run();
    }
}
