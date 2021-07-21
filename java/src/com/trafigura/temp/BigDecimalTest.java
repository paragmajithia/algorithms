package com.trafigura.temp;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Stack;

public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal("22000.1230");
        System.out.println(new BigDecimal(bd.stripTrailingZeros().toPlainString())); // Result is 23000

        int num = 'A';
        System.out.println("Num:" + num);

        String testStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.println("Uppercase");
        for (char c : testStr.toCharArray()) {
            System.out.print((int) c + ",");
        }
        System.out.println("\nLowercase");
        testStr = testStr.toLowerCase();
        for (char c : testStr.toCharArray()) {
            System.out.print((int) c + ",");
        }

        System.out.println("\nInt to char conversion");
        for (int i =0; i<512; i++) {
            System.out.println(i +":" + (char)i);
        }

        System.out.println("Integer representation of T:" + (int) 'T');
        System.out.println("Byte representation of T:" + Integer.toString( 'T', 2));

        System.out.println("Available characterset:\n" + Charset.availableCharsets());
        System.out.println("Default characterset:\n" + Charset.defaultCharset());

    }
}
