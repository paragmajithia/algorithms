package com.facebook.heaps.maxtripleproduct;

import java.util.*;
// Add any extra import statements you may need here


class MaxTripleProduct {

    // Add any helper functions you may need here


    int[] findMaxProduct(int[] arr) {
        // Initialize response array, variables
        int[] resp = new int[arr.length];
        resp[0] = -1;

        // Edge case
        if(arr.length <= 1) {
            return resp;
        } else if (arr.length <= 2) {
            resp[1] = -1;
            return resp;
        } else {
            resp[1] = -1;
        }

        // Initialize the product
        int product = arr[0] * arr[1] * arr[2];
        resp[2] = product;
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        queue.add(arr[0]);
        queue.add(arr[1]);
        queue.add(arr[2]);

        // Loop through the array
        int index = 3;
        while (index < arr.length) {

            if (arr[index] > queue.peek()) {
                int removed = queue.remove();
                queue.add(arr[index]);
                product = product / removed;
                product = product * arr[index];
                resp[index] = product;
            } else {
                resp[index] = product;
            }
            index++;
        }
        return resp;
    }












    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void check(int[] expected, int[] output) {
        int expected_size = expected.length;
        int output_size = output.length;
        boolean result = true;
        if (expected_size != output_size) {
            result = false;
        }
        for (int i = 0; i < Math.min(expected_size, output_size); i++) {
            result &= (output[i] == expected[i]);
        }
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printIntegerArray(expected);
            System.out.print(" Your output: ");
            printIntegerArray(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printIntegerArray(int[] arr) {
        int len = arr.length;
        System.out.print("[");
        for(int i = 0; i < len; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.print("]");
    }

    public void run() {
        int[] arr_1 = {1, 2, 3, 4, 5};
        int[] expected_1 = {-1, -1, 6, 24, 60};
        int[] output_1 = findMaxProduct(arr_1);
        check(expected_1, output_1);

        int[] arr_2 = {2, 4, 7, 1, 5, 3};
        int[] expected_2 = {-1, -1, 56, 56, 140, 140};
        int[] output_2 = findMaxProduct(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here
        int[] arr_3 = {1};
        int[] expected_3 = {-1};
        int[] output_3 = findMaxProduct(arr_3);
        check(expected_3, output_3);

        int[] arr_4 = {3, 4};
        int[] expected_4 = {-1, -1};
        int[] output_4 = findMaxProduct(arr_4);
        check(expected_4, output_4);

        int[] arr_5 = {2, 3, 2, 2, 4, 4, 5, 5, 5, 5, 5};
        int[] expected_5 = {-1, -1, 12, 12, 24, 48, 80, 100, 125, 125, 125};
        int[] output_5 = findMaxProduct(arr_5);
        check(expected_5, output_5);

    }

    public static void main(String[] args) {
        new MaxTripleProduct().run();
    }
}