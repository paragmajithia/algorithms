package com.facebook.graphs.permutations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class Main {

    // Add any helper functions you may need here
    public int[] reverse(int[] arr, int start, int end) {

        int[] resp = arr.clone();
        for (int i = start, j = end; i < j; i++, j--) {
            resp[i] = arr[j];
            resp[j] = arr[i];
        }
        return resp;
    }

    class Node {
        public int[] arr;
        public int count;
        Node(int[] arr, int count) {
            this.arr = arr;
            this.count = count;
        }
    }


    int minOperations(int[] arr) {
        // Write your code here
        int[] targArr = arr.clone();
        Arrays.sort(targArr);
        String orig = Arrays.toString(arr);
        String target = Arrays.toString(targArr);
        System.out.println("Inpout array: " + orig);
        System.out.println("Target Array: " + target);

        if(orig.equals(target)) {
            return 0;
        }

        Node node = new Node(arr, 0);
        Set<String> visited = new HashSet<>();
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.push(node);
        visited.add(orig);

        while (!queue.isEmpty()) {
            node = queue.poll();

            for (int i=0; i < node.arr.length - 1; i++) {
                for (int j=i+1; j < node.arr.length; j++){
                    // reverse the element
                    int[] reversed = reverse(node.arr, i, j);
                    String reversedStr = Arrays.toString(reversed);
                    if (reversedStr.equals(target)) {
                        return node.count + 1;
                    }
                    if (!visited.contains(reversedStr)) {
                        visited.add(reversedStr);
                        queue.add(new Node(reversed, node.count + 1));
                    }
                }
            }
        }







        return -1;
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

        int n_1 = 5;
        int[] arr_1 = {1, 2, 5, 4, 3};
        int expected_1 = 1;
        int output_1 = minOperations(arr_1);
        check(expected_1, output_1);

        int n_2 = 3;
        int[] arr_2 = {3, 1, 2};
        int expected_2 = 2;
        int output_2 = minOperations(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here
        int n_3 = 3;
        int[] arr_3 = {1,2,3};
        int expected_3 = 0;
        int output_3 = minOperations(arr_3);
        check(expected_3, output_3);

        int n_4 = 4;
        int[] arr_4 = {2,3,1,4};
        int expected_4 = 2;
        int output_4 = minOperations(arr_4);
        check(expected_4, output_4);

        int n_5 = 5;
        int[] arr_5 = {4,1,3,2};
        int expected_5 = 2;
        int output_5 = minOperations(arr_5);
        check(expected_5, output_5);

    }
    public static void main(String[] args) {
        new Main().run();
    }
}
