package com.leetcode.dynamicprogramming;

/*
A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

"AAJF" with the grouping (1 1 10 6)
"KJF" with the grouping (11 10 6)
Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

Given a string s containing only digits, return the number of ways to decode it.

The answer is guaranteed to fit in a 32-bit integer.



Example 1:

Input: s = "12"
Output: 2
Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: s = "226"
Output: 3
Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
Example 3:

Input: s = "0"
Output: 0
Explanation: There is no character that is mapped to a number starting with 0.
The only valid mappings with 0 are 'J' -> "10" and 'T' -> "20", neither of which start with 0.
Hence, there are no valid ways to decode this since all digits need to be mapped.
Example 4:

Input: s = "06"
Output: 0
Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").


Constraints:

1 <= s.length <= 100
s contains only digits and may contain leading zero(s).
 */
public class NumberDecoding {

    public int numDecodings(String s) {

        // Return 0 for invalid input
        if (s == null || s.isEmpty() || s.startsWith("0")) {
            return 0;
        }

        // DP array for storing previous result
        int dp[] = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1; // First letter

        // Assumption as 1 assuming all char  individually decoded
        // (for -2 condition)
        // case 1 -- Any digit is prefixed with 1
        // case 2 -- digit is prefixed with 2 and current digit <=6
        // case 3 -- digit is  suffixed with 0 then consider as single digit and ignore -2 condition
        // Edge case: string starting with 0 -- return 0
        // Ex:
        // 232510 ==> BCAEJ OR WBEJ OR WYJ OR BCYA
        // 222210 ==> BBBBA OR VVA OR BVBA OR BBVA OR VBBA
        //
        // dp[-1] = 1
        // dp[0] = 1 => B
        // dp[1] = dp[0] * 1 + dp[-1] * 1 = 2 ==> BB and V
        // dp[2] = dp[1] * 1 + dp[0] * 1 = 2 + 1 = 3 ==> BBB and VB and BV
        // dp[3] = dp[2] * 1 + dp[1] * 1 = 3 + 2 = 5 ==> BBBB and VBB and BVB and BBV and VV
        // Loop thr all character
        for (int i = 0; i <s.length(); i++) {

            // Ex: 12003 -- Extra invalid 0 before 3
            if (s.charAt(i) == '0') {
                return 0;
            }

            // suffix 0 condition
            // OR exact 0 condition
            if ((i < (s.length() - 1) && s.charAt(i+1) == '0') && Character.getNumericValue(s.charAt(i)) > 2)  {
                // Ex: 230 -- 30 is invalid
                return 0;
            } else if ((i < (s.length() - 1) && s.charAt(i+1) == '0')) {
                // ex: 220 Or 210
                dp[i+1] = dp[i];
                i = i + 1;
                dp[i+1] = dp[i];
                continue;
            }

            // Copy previous dp value
            dp[i+1] = dp[i];

            // Check prefix condition
            // Ex: 21 where i is at 1
            if (i > 0 &&
                    (s.charAt(i-1) == '1' ||
                            (s.charAt(i-1) == '2' && Character.getNumericValue(s.charAt(i)) <= 6 ))) {
                dp[i+1] = dp[i+1] + dp[i-1];
            }
        }

        return dp[s.length()];

    }
}
