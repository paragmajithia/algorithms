package com.facebook.test;
/*
A photography set consists of NN cells in a row, numbered from 11 to NN in order, and can be represented by a string CC of length NN. Each cell ii is one of the following types (indicated by C_iC
i
​	 , the iith character of CC):
If C_iC
i
​	  = “P”, it is allowed to contain a photographer
If C_iC
i
​	  = “A”, it is allowed to contain an actor
If C_iC
i
​	  = “B”, it is allowed to contain a backdrop
If C_iC
i
​	  = “.”, it must be left empty
A photograph consists of a photographer taking a picture of both an actor and a backdrop, such that each of them is placed in a valid cell, and such that the actor is between the photographer and the backdrop. Such a photograph is considered artistic if the distance between the photographer and the actor is between XX and YY cells (inclusive), and the distance between the actor and the backdrop is also between XX and YY cells (inclusive). The distance between cells ii and jj is |i - j|∣i−j∣ (the absolute value of the difference between their indices).
Determine the number of different artistic photographs which could potentially be taken at the set. Two photographs are considered different if they involve a different photographer cell, actor cell, and/or backdrop cell.
Constraints
1 \le N \le 2001≤N≤200
1 \le X \le Y \le N1≤X≤Y≤N
Sample Test Case #1
N = 5
C = APABA
X = 1
Y = 2
Expected Return Value = 1
Sample Test Case #2
N = 5
C = APABA
X = 2
Y = 3
Expected Return Value = 0
Sample Test Case #3
N = 8
C = .PBAAP.B
X = 1
Y = 3
Expected Return Value = 3
Sample Explanation
In the first case, the absolute distances between photographer/actor and actor/backdrop must be between 11 and 22. The only possible photograph that can be taken is with the 33 middle cells, and it happens to be artistic.
In the second case, the only possible photograph is again taken with the 33 middle cells. However, as the distance requirement is between 22 and 33, it is not possible to take an artistic photograph.
In the third case, there are 44 possible photographs, illustrated as follows:
.P.A...B
.P..A..B
..BA.P..
..B.AP..
All are artistic except the first, where the artist and backdrop exceed the maximum distance of 33.
 */

public class DirectorPhoto {
    public static void main(String[] args) {
        System.out.println( "Ans:" + (new DirectorPhoto()).getArtisticPhotographCount(5, "BAPABAP", 1,1));
    }
    public int getArtisticPhotographCount(int N, String C, int X, int Y) {
        // Write your code here
        int ans = 0;
        int countLeftP =0; int countLeftB =0;
        int countRightP =0; int countRightB =0;

        // Get count of right from min distance to max -1
        for (int i=X-1; i< C.length() && i <= Y -1; i++) {
            if (C.charAt(i) == 'P') {
                countRightP++;
            }
            if (C.charAt(i) == 'B') {
                countRightB++;
            }
        }
        System.out.println("Initial Right count: RightP:" + countRightP + " RightB: " + countRightB);

        for (int i=0; i< C.length(); i++) {
            int leftStart = i - X; int leftEnd = i - Y;
            int rightStart = i + X; int rightEnd = i + Y;
            // System.out.println("Left: " + leftStart + "-" + leftEnd + ", Right: " + rightStart + "-" + rightEnd);

            // Increment newly added character from left
            if (leftStart >= 0 && C.charAt(leftStart) == 'P') {
                countLeftP++;
            } else if (leftStart >= 0 && C.charAt(leftStart) == 'B') {
                countLeftB++;
            }

            // Decrement newly removed character from left
            if ((leftEnd-1) >= 0 && C.charAt(leftEnd-1) == 'P') {
                countLeftP--;
            } else if (leftEnd-1 >= 0 && C.charAt(leftEnd-1) == 'B') {
                countLeftB--;
            }

            // Increment newly added character from right
            if (rightEnd < C.length() && C.charAt(rightEnd) == 'P') {
                countRightP++;
            } else if (rightEnd < C.length() && C.charAt(rightEnd) == 'B') {
                countRightB++;
            }

            // Decrement newly removed character from right
            if ((rightStart-1) < C.length() && C.charAt(rightStart-1) == 'P') {
                countRightP--;
            } else if ((rightStart-1) < C.length() && C.charAt(rightStart-1) == 'B') {
                countRightB--;
            }

            System.out.println("Count when Index: " + i + ", Left P: " + countLeftP + ", Left B:" + countLeftB
                    + ", RightP:" + countRightP + " RightB: " + countRightB);
            if (C.charAt(i) == 'A') {
                ans = ans + countLeftP * countRightB + countLeftB * countRightP;
            }


        }
        return ans;
    }

}
