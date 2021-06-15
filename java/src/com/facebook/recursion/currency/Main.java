package com.facebook.recursion.currency;

import java.util.*;
// Add any extra import statements you may need here


class Main {

    // Add any helper functions you may need here
    boolean isExact(int target, int[] denominations, int total, Map<Integer, Boolean> mem) {
        //System.out.println("Target: " + target + ", total: " + total + ", Denom: " + Arrays.toString(denominations));

        if (mem.containsKey(total)) {
            return mem.get(total);
        }

        boolean isExact = false;
        for (int i=0; i< denominations.length; i++){
            if (total + denominations[i] == target || (target % (total + denominations[i]) == 0)) {
                return true;
            } else if ((total + denominations[i]) > target) {
                continue;
            } else {
                if (isExact(target, denominations, total + denominations[i], mem)) {
                    isExact = true;
                    mem.put(total, true);
                    break;
                }
            }
        }
        mem.put(total, isExact);
        return isExact;
    }

    boolean canGetExactChange(int targetMoney, int[] denominations) {
        // Write your code here
        int lastIndex = -1;
        Arrays.sort(denominations);
        for (int i =0; i< denominations.length; i++) {
            if (targetMoney == denominations[i]) {
                return true;
            } else if (targetMoney > denominations[i]) {
                lastIndex = i;
            } else {
                break;
            }
        }
        //System.out.println("LastIndex: " + lastIndex + ", denom: " + Arrays.toString(denominations));

        if (lastIndex > -1) {
            return isExact(targetMoney, Arrays.copyOfRange(denominations, 0, lastIndex+1), 0,
                    new HashMap<Integer, Boolean>());
        }

        return false;
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
        System.out.print("[\"" + str + "\"]");
    }

    public void run() {
        int target_1 = 94;
        int arr_1[] = {5, 10, 25, 100, 200};
        boolean expected_1 = false;
        boolean output_1 = canGetExactChange(target_1, arr_1);
        check(expected_1, output_1);

        int target_2 = 75;
        int arr_2[] = {4, 17, 29};
        boolean expected_2 = true;
        boolean output_2 = canGetExactChange(target_2, arr_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new Main().run();
    }
}