package main.java.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem2748 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int fibonacciCount = Integer.parseInt(br.readLine());
        long[] fibonacci = new long[fibonacciCount + 1];

        fibonacci[0] = 0L;
        fibonacci[1] = 1L;
        for (int i = 2; i < fibonacci.length; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }

        bw.write(fibonacci[fibonacciCount] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
