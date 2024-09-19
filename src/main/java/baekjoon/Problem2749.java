package main.java.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 피사노 주기를 이용하여 풀이해야 한다.
 * n번째 피보나치 수를 1_000_000으로 나눈 나머지 K를 구하기 위해 n번 반복하는 게 아니라,
 * 피사노 주기 M의 주기에 대응해 K의 집합이 반복된다는 사실을 이용함으로써 n번 반복이 아닌 M번만 반복하여 시간을 단축해야 한다.
 * 피사노 주기를 찾는 공식은 있지만, 만약 기억이 안난다면 0번째, 1번째 피보나치 수를 1_000_000번 나눈 나머지는 0, 1이란 걸 이용해
 * 연속으로 0, 1이 나오는 경우를 찾아 주기를 예측할 수 있다.
 */
public class Problem2749 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final long MILLION = 1_000_000L;
        final long PISANO_PERIOD = 15 * Math.round(Math.pow(10, 6 - 1));

        int fibonacciCount = Math.toIntExact(Long.parseLong(br.readLine()) % PISANO_PERIOD);
        if (fibonacciCount == 0 || fibonacciCount == 1) {
            bw.write(fibonacciCount % MILLION + "\n");
        } else {
            long[] fibonacci = new long[fibonacciCount + 1];
            fibonacci[0] = 0 % MILLION;
            fibonacci[1] = 1 % MILLION;
            for (int i = 2; i < fibonacciCount + 1; i++) {
                fibonacci[i] = (fibonacci[i - 1] + fibonacci[i - 2]) % MILLION;
            }

            bw.write(fibonacci[fibonacciCount] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
