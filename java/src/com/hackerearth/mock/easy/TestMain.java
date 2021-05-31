package com.hackerearth.mock.easy;

import java.io.*;

public class TestMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int N = Integer.parseInt(br.readLine().trim());
        int[] A = new int[N];
        String[] arr_A = br.readLine().split(" ");
        for(int i_A = 0; i_A < N; i_A++) {
            A[i_A] = Integer.parseInt(arr_A[i_A]);
        }
        int out_ = Solve(N, A);
        System.out.println(out_);

        wr.close();
        br.close();
    }
    static int Solve(int N, int[] A){
        int min = A[0];

        for(int index = 1; index < A.length; index++){
            if (Math.abs(A[index]) < Math.abs(min)) {
                min = A[index];
            } else if (Math.abs(A[index]) == Math.abs(min) && A[index] > min) {
                min = A[index];
            }
        }

        return min;
    }
}
