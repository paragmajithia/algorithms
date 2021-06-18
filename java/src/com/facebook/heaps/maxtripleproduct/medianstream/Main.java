package com.facebook.heaps.maxtripleproduct.medianstream;

import java.io.*;
import java.util.*;
// Add any extra import statements you may need here


class Main {

    // Add any helper functions you may need here


    int[] findMedian(int[] arr) {
        // Write your code here

        PriorityQueue<Integer> leftHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        PriorityQueue<Integer> rightHeap = new PriorityQueue<Integer>();
        int[] resp = new int[arr.length];
        leftHeap.add(arr[0]);
        resp[0] = arr[0];
        int median = resp[0];
        //System.out.println("Input: " + Arrays.toString(arr));

        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            // Case 1 -- leftHeap size greater
            if (leftHeap.size() > rightHeap.size()) {
                if (leftHeap.peek() < current) {
                    rightHeap.add(current);
                } else {
                    int removed = leftHeap.remove();
                    rightHeap.add(removed);
                    leftHeap.add(current);
                }
                median = (leftHeap.peek() + rightHeap.peek())/2;
                // Case 2 -- Right Heap is greater
            } else if (leftHeap.size() < rightHeap.size()) {
                if (rightHeap.peek() > current) {
                    leftHeap.add(current);
                } else {
                    int removed = rightHeap.remove();
                    leftHeap.add(removed);
                    rightHeap.add(current);
                }
                median = (leftHeap.peek() + rightHeap.peek())/2;
            } else {
                if (current < median) {
                    leftHeap.add(current);
                    median = leftHeap.peek();
                } else {
                    rightHeap.add(current);
                    median = rightHeap.peek();
                }
            }
            // System.out.println("Left: " + leftHeap + ", Right: " + rightHeap + ", Median: " + median);
            resp[i] = median;
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
        int[] arr_1 = {5, 15, 1, 3};
        int[] expected_1 = {5, 10, 5, 4};
        int[] output_1 = findMedian(arr_1);
        check(expected_1, output_1);

        int[] arr_2 = {2, 4, 7, 1, 5, 3};
        int[] expected_2 = {2, 3, 4, 3, 4, 3};
        int[] output_2 = findMedian(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here
        int[] arr_3 = {2, 4, 7, 1, 5, 3,6,12,8};
        int[] expected_3 = {2, 3, 4, 3, 4, 3, 4,4,5};
        int[] output_3 = findMedian(arr_3);
        check(expected_3, output_3);

    }
    public static void main(String[] args) {
        new Main().run();
    }
}
