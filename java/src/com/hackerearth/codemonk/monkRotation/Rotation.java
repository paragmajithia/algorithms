package com.hackerearth.codemonk.monkRotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Rotation {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int testCases;

    public static void main(String[] args) throws IOException {

        // Read number of test cases
        testCases = Integer.parseInt(reader.readLine().trim());

        // Process each input
        for (int i = 0; i < testCases; i++) {
            processTestCase();
        }
    }

    public static void processTestCase() throws IOException {

        // Read input data
        // Get Array size and number of rotaion & array itself
        String[] meta = reader.readLine().trim().split("\\s");
        int size = Integer.parseInt(meta[0]);
        int rot = Integer.parseInt(meta[1]);
        String input = reader.readLine().trim();

        // Update rot to mod value
        rot = rot % size;

        // if no rotation to be done then print input
        if (rot == 0) {
            System.out.println(input);
        } else {
            int index = input.lastIndexOf(" ", input.length()-1);
            int count = 1;
            while (index > 0 && count < rot) {
                index = input.lastIndexOf(" ", index - 1);
                count++;
            }
            if (index < 0) {
                System.out.println(input);
            } else {
                System.out.println(input.substring(index+1) + " " + input.substring(0, index));
            }
        }
    }
}
