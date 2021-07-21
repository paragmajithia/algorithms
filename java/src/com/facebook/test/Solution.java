package com.facebook.test;// Write any import statements here

import java.util.*;
class Solution {

    public long getMaxAdditionalDinersCount(long N, long K, int M, long[] S) {
        // Write your code here

        // Sort S
        Arrays.sort(S);

        // Check seat avaiblable
        long addSeats = 0;
        long startSeat = 1;
        for (int i=0; i<S.length; i++) {
            long diff = S[i] - (K + 1) - startSeat + 1;
            if (diff > 0) {
                long available = (long) (Math.floor(diff/ (K + 1)) + 1);
                addSeats = addSeats + available;
            }
            startSeat =  S[i] + (K+1);
        }

        if (startSeat < N) {
            long diff = N - startSeat + 1;
            if (diff > 0) {
                long available = (long) (Math.floor(diff/ (K + 1)) + 1);
                addSeats = addSeats + available;
            }
        }
        return addSeats;
    }

}
