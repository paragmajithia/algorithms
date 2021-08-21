package com.trafigura.temp;

public class BitsManipulation {
    public static void main(String[] args) {

        // Set 15th bit to 1
        System.out.println("15th bit to 1:");
        System.out.println(  Integer.toBinaryString(1 << 14));

        // Setting all bits to 1 (Ex: set 28 bits to 1)
        System.out.println("Setting all 28 bits to 1");
        System.out.println(  Integer.toBinaryString(1 << 28));
        System.out.println( Integer.toBinaryString((1 << 28) -1));

        // Flipping the bit of number
        System.out.println("Test for inverting the bit:");
        int numFlip = 13; // 1101
        System.out.println(  "Number before split: " + Integer.toBinaryString(numFlip));
        int totalBits = (int) (Math.log(numFlip)/Math.log(2)) + 1;
        for (int i = 0; i < totalBits; i++) {
            numFlip = numFlip ^ (1 << i);
        }
        System.out.println(  "Number after split: " + Integer.toBinaryString(numFlip));


    }
}
