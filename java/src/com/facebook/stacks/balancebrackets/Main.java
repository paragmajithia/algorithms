package com.facebook.stacks.balancebrackets;

import java.util.*;
// Add any extra import statements you may need here


class Main {
    // Add any helper functions you may need here
    Map<Character, Character> charMap = new HashMap<Character, Character>() {
        {
            put('(', ')');
            put('{', '}');
            put('[', ']');
        }

    };

    boolean isBalanced(String s) {

        ArrayDeque<Character> stack = new ArrayDeque<Character>();
        if (s.length() % 2 != 0) {
            return false;
        }

        for (int index = 0; index < s.length(); index++){
            if (charMap.keySet().contains(s.charAt(index))) {
                stack.addLast(s.charAt(index));
            } else {
                Character curr = stack.removeLast();
                if (curr == null ||
                        (charMap.containsKey(curr) &&
                                !charMap.get(curr).equals(s.charAt(index)))) {
                    return false;
                }
            }
        }

        if (stack.isEmpty()) {
            return true;
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
        System.out.print("[" + str + "]");
    }

    public void run() {
        String s_1 = "{[(])}";
        boolean expected_1 = false;
        boolean output_1 = isBalanced(s_1);
        check(expected_1, output_1);

        String s_2 = "{{[[(())]]}}";
        boolean expected_2 = true;
        boolean output_2 = isBalanced(s_2);
        check(expected_2, output_2);

        // Add your own test cases here
        String s_3 = "{";
        boolean expected_3 = false;
        boolean output_3 = isBalanced(s_3);
        check(expected_3, output_3);

        String s_4 = "{}()[]{{{()}}}";
        boolean expected_4 = true;
        boolean output_4 = isBalanced(s_4);
        check(expected_4, output_4);

        String s_5 = "{}()[]{{{({)}}}";
        boolean expected_5 = false;
        boolean output_5 = isBalanced(s_5);
        check(expected_5, output_5);

    }

    public static void main(String[] args) {
        new Main().run();
    }
}