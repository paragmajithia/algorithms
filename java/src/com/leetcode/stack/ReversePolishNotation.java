package com.leetcode.stack;

import java.util.*;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
 *
 * Note that division between two integers should truncate toward zero.
 *
 * It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.
 *
 *
 *
 * Example 1:
 *
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 *
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 *
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 *
 * Constraints:
 *
 * 1 <= tokens.length <= 104
 * tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 */
public class ReversePolishNotation {

    public int evalRPN(String[] tokens) {

        Deque<String> deq = new ArrayDeque<String>();
        deq.addLast(tokens[0]);

        for (int i = 1; i < tokens.length; i++) {

            if (!(tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/"))) {
                deq.addLast(tokens[i]);
                continue;
            }

            // Fetch last 2 nums and push result
            Integer secondNum = Integer.parseInt(deq.removeLast());
            Integer firstNum = Integer.parseInt(deq.removeLast());
            String oper = tokens[i];

            if (oper.equals("+")) {
                deq.addLast(Integer.toString(firstNum + secondNum));
            } else if (oper.equals("-")) {
                deq.addLast(Integer.toString(firstNum - secondNum));
            } else if (oper.equals("*")) {
                deq.addLast(Integer.toString(firstNum * secondNum));
            }else if (oper.equals("/")) {
                deq.addLast(Integer.toString(firstNum / secondNum));
            }
        }

        return Integer.parseInt(deq.removeLast());

    }
}
