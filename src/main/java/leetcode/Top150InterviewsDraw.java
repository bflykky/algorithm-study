package main.java.leetcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Top150InterviewsDraw {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./problem_list.txt"));

        Set<String> problemList = new HashSet<>();
        String problem = br.readLine();
        while (problem != null) {
            problemList.add(problem);
            problem = br.readLine();
        }

        System.out.println(problemList.size());
        System.out.printf("오늘 풀 문제: %s\n", problemList.stream().findFirst().get());

    }
}
