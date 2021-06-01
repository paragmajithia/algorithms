package com.trafigura.temp;

import java.math.BigDecimal;
import java.util.Stack;

public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal("22000.1230");
        System.out.println(new BigDecimal(bd.stripTrailingZeros().toPlainString())); // Result is 23000

        Stack testStack = new Stack();

    }
}
