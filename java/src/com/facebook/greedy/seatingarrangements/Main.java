package com.facebook.greedy.seatingarrangements;

import java.util.*;
// Add any extra import statements you may need here


class Main {

    // Add any helper functions you may need here


    int minOverallAwkwardness(int[] arr) {
        // Write your code here
        int diff = 0;
        int left = arr[0];
        int right = arr[0];
        boolean isLeft = true;
        Arrays.sort(arr);
        for (int i =1; i < arr.length; i++) {
            if (isLeft) {
                if (diff < (arr[i] - left)) {
                    diff = arr[i] - left;
                }
                left = arr[i];
            } else {
                if (diff < (arr[i] - right)) {
                    diff = arr[i] - right;
                }
                right = arr[i];
            }
            isLeft = !isLeft;
        }
        return diff;
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
        int[] arr_1 = {5, 10, 6, 8};
        int expected_1 = 4;
        int output_1 = minOverallAwkwardness(arr_1);
        check(expected_1, output_1);

        int[] arr_2 = {1, 2, 5, 3, 7};
        int expected_2 = 4;
        int output_2 = minOverallAwkwardness(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new Main().run();
    }
}
