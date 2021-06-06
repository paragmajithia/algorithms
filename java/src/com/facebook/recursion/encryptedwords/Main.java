package com.facebook.recursion.encryptedwords;

public class Main {
    // Add any helper functions you may need here
    /* ab, abcd, abcde, abcdefg
     * Even: divide by 2 - 1, Odd: Divide by 2
     *  4 ==> 1 (0, 3), (2,5) => 3
     *  5 ==> 2 (0,4), (2,6) => 4
     *  6 ==> 2 (0,5), (2,7) => 4
     *  7 ==> 3,(2,8) => 5
     *  8 ==> 3
     */

    void updateString(String s, int start, int end, StringBuilder sb) {


        // Base case
        if (start > end) {
            return;
        }
        System.out.println("Start: " + start + ", End: " + end);
        // Get mid point
        int mid = start + (end - start) / 2;
        sb.append(s.charAt(mid));

        updateString(s, start, mid -1, sb);
        updateString(s, mid + 1, end, sb);

    }

    String findEncryptedWord(String s) {

        StringBuilder sb = new StringBuilder("");
        updateString(s, 0, s.length() - 1, sb);
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
        String s_1 = "abc";
        String expected_1 = "bac";
        String output_1 = findEncryptedWord(s_1);
        check(expected_1, output_1);

        String s_2 = "abcd";
        String expected_2 = "bacd";
        String output_2 = findEncryptedWord(s_2);
        check(expected_2, output_2);

        // Add your own test cases here
        String s_3 = "a";
        String expected_3 = "a";
        String output_3 = findEncryptedWord(s_3);
        check(expected_3, output_3);

        String s_4 = "abcdefggggg";
        String expected_4 = "fcabdeggggg";
        String output_4 = findEncryptedWord(s_4);
        check(expected_4, output_4);

    }

    public static void main(String[] args) {
        new Main().run();
    }
}
