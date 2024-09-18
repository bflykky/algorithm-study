package main.java.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem2749 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final long MILLION = 1_000_000L;
        final long PISANO_PERIOD = 15 * Math.round(Math.pow(10, 6 - 1));

        int fibonacciCount = Math.toIntExact(Long.parseLong(br.readLine()) % PISANO_PERIOD);
        if (fibonacciCount == 0 || fibonacciCount == 1) {
            bw.write(fibonacciCount + "\n");
        } else {
            long formerAndFormerFibonacci = 0L;
            long formerFibonacci = 1L;
            long result = 0L;
            for (int i = 2; i < fibonacciCount + 1; i++) {
                result = (formerFibonacci % MILLION) + (formerAndFormerFibonacci % MILLION) % MILLION;
                formerAndFormerFibonacci = formerFibonacci % MILLION;
                formerFibonacci = result;
            }

            bw.write(result % MILLION + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
