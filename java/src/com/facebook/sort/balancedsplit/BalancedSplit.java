package com.facebook.sort.balancedsplit;

import java.io.*;
import java.util.*;
// Add any extra import statements you may need here


class BalancedSplit {

    // Add any helper functions you may need here
    boolean isSplitExists(int[] arr, int[] cum, int start, int end, int sum ){

        if (start > end) {
            return false;
        }

        int mid = (start + end) / 2;
        if (cum[mid] == sum) {
            if ((mid < arr.length - 1) && arr[mid] != arr[mid+1]) {
                return true;
            } else {
                return false;
            }
        } else if (cum[mid] < sum) {
            return isSplitExists(arr, cum, mid+1, end, sum );
        } else {
            return isSplitExists(arr, cum, start, mid - 1, sum);
        }

    }

    boolean balancedSplitExists(int[] arr) {
        // Write your code here
        Arrays.sort(arr);

        int[] cum = new int[arr.length];
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total = total + arr[i];
            cum[i] = total;
        }

        if (total % 2 != 0) {
            return false;
        }

        return isSplitExists(arr, cum, 0, arr.length, total / 2 );


    }











    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;
    void check(boolean expected, boolean output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            System.out.print(expected);
            System.out.print(" Your output: ");
            System.out.print(output);
            System.out.println();
        }
        test_case_number++;
    }
    void printString(String str) {
        System.out.print("[" + str + "]");
    }

    public void run() {
        int arr_1[] = {2, 1, 2, 5};
        boolean expected_1 = true;
        boolean output_1 = balancedSplitExists(arr_1);
        check(expected_1, output_1);

        int arr_2[] = {3, 6, 3, 4, 4};
        boolean expected_2 = false;
        boolean output_2 = balancedSplitExists(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here
        int arr_3[] = {1,0,0,2,3};
        boolean expected_3 = true;
        boolean output_3 = balancedSplitExists(arr_3);
        check(expected_3, output_3);

    }

    public static void main(String[] args) {
        new BalancedSplit().run();
    }
}
