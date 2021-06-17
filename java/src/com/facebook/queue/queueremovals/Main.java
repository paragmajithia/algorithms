package com.facebook.queue.queueremovals;

import java.util.*;
// Add any extra import statements you may need here


class Main {

    // Add any helper functions you may need here
    class Node {
        int num;
        int index;
        Node(int num, int index) {
            this.num = num;
            this.index = index;
        }
        public String toString() {
            return String.format("Num:%s,Index:%s", num, index);
        }
    }

    int[] findPositions(int[] arr, int x) {
        // Write your code here
        LinkedList<Node> list = new LinkedList<Node>();
        for (int i = 0; i < arr.length; i++) {
            list.addLast(new Node(arr[i], i+1));
        }
        //System.out.println("Input: " + Arrays.toString(arr));
        //System.out.println("List of node: " + list);
        int[] response = new int[x];

        // Perform x iterations
        for (int i=0; i < x; i++) {

            // Pop x elements
            int count = 0;
            int maxIndex = 0;
            Node maxNode = null;
            List<Node> poppedList = new ArrayList<Node>();

            while (!list.isEmpty() && count < x) {
                Node node = list.removeFirst();

                // Track maximum node
                if (maxNode == null || maxNode.num < node.num) {
                    maxNode = node;
                    maxIndex = count;
                }
                poppedList.add(node);
                count++;
            }

            // Append to the list decremented node (except for highest one)
            for (int j = 0; j< poppedList.size(); j++) {
                if (j == maxIndex) {
                    response[i] = maxNode.index;
                } else {
                    poppedList.get(j).num = poppedList.get(j).num <= 0?0:poppedList.get(j).num - 1;
                    list.addLast(poppedList.get(j));
                }
            }

        }

        return response;


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
        int n_1 = 6;
        int x_1 = 5;
        int[] arr_1 = {1, 2, 2, 3, 4, 5};
        int[] expected_1 = {5, 6, 4, 1, 2 };
        int[] output_1 = findPositions(arr_1, x_1);
        check(expected_1, output_1);

        int n_2 = 13;
        int x_2 = 4;
        int[] arr_2 = {2, 4, 2, 4, 3, 1, 2, 2, 3, 4, 3, 4, 4};
        int[] expected_2 = {2, 5, 10, 13};
        int[] output_2 = findPositions(arr_2, x_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new Main().run();
    }
}