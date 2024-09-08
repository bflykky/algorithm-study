package main.java.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Stack;

public class Problem1918 {
    public static void main(String[] args) throws IOException {
        final List<String> MAJOR_OPERATOR_LIST = List.of("*", "/");
        final List<String> MINOR_OPERATOR_LIST = List.of("+", "-");
        final String LEFT_BRACKET = "(";
        final String RIGHT_BRACKET = ")";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        String[] splitInput = input.split("");

        Stack<String> operatorStack = new Stack<>();
        String output = "";
        for (int i = 0; i < splitInput.length; i++) {
            String tmp = splitInput[i];
            if (MINOR_OPERATOR_LIST.contains(tmp)) {
                while (!operatorStack.isEmpty()
                        && ((MAJOR_OPERATOR_LIST.contains(operatorStack.peek()) || MINOR_OPERATOR_LIST.contains(operatorStack.peek())))) {
                    output = output.concat(operatorStack.pop());
                }
                operatorStack.push(tmp);
            } else if (MAJOR_OPERATOR_LIST.contains(tmp)) {
                while (!operatorStack.isEmpty() && MAJOR_OPERATOR_LIST.contains(operatorStack.peek())) {
                    output = output.concat(operatorStack.pop());
                }
                operatorStack.push(tmp);
            } else if (LEFT_BRACKET.equals(tmp)) {
                operatorStack.push(tmp);
            } else if (RIGHT_BRACKET.equals(tmp)) {
                while (!LEFT_BRACKET.equals(operatorStack.peek())) {
                    output = output.concat(operatorStack.pop());
                }

                operatorStack.pop(); // 좌측 소괄호 삭제
            } else { // 피연산자인 경우
                output = output.concat(tmp);
            }
        }

        while (!operatorStack.isEmpty()) {
            output = output.concat(operatorStack.pop());
        }

        bw.write(output);
        bw.flush();

        bw.close();
        br.close();
    }
}
