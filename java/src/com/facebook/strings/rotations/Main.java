package com.facebook.strings.rotations;

import java.util.*;
// Add any extra import statements you may need here


class Main {

    // Add any helper functions you may need here
    char rotateUpperCase(char letter, int factor) {
        return (char) (((letter - 'A') + factor) % 26 + 'A');
    }

    char rotateLowerCase(char letter, int factor) {
        return (char) (((letter - 'a') + factor) % 26 + 'a');
    }

    char rotatenumber(char letter, int factor) {
        return (char) (((letter - '0') + factor) % 10 + '0');
    }

    String rotationalCipher(String input, int rotationFactor) {
        // Write your code here
        int alphaFactor = rotationFactor % 26;
        int numFactor = rotationFactor % 10;


        StringBuilder sb = new StringBuilder("");
        for (int i=0; i < input.length(); i++) {
            if(input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') {
                sb.append(rotateUpperCase(input.charAt(i), alphaFactor));
            } else if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z') {
                sb.append(rotateLowerCase(input.charAt(i), alphaFactor));
            } else if (Character.toString(input.charAt(i)).matches("[0-9]")) {
                sb.append(rotatenumber(input.charAt(i), numFactor));
            } else {
                sb.append(input.charAt(i));
            }
        }
        return sb.toString();
    }











    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;
    void check(String expected, String output) {
        boolean result = (expected.equals(output));
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printString(expected);
            System.out.print(" Your output: ");
            printString(output);
            System.out.println();
        }
        test_case_number++;
    }
    void printString(String str) {
        System.out.print("[\"" + str + "\"]");
    }

    public void run() {
        String input_1 = "All-convoYs-9-be:Alert1.";
        int rotationFactor_1 = 4;
        String expected_1 = "Epp-gsrzsCw-3-fi:Epivx5.";
        String output_1 = rotationalCipher(input_1, rotationFactor_1);
        check(expected_1, output_1);

        String input_2 = "abcdZXYzxy-999.@";
        int rotationFactor_2 = 200;
        String expected_2 = "stuvRPQrpq-999.@";
        String output_2 = rotationalCipher(input_2, rotationFactor_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new Main().run();
    }
}
