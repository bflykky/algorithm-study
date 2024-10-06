package main.java.leetcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Top150InterviewsDraw {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./problem_list.txt"));
        String str = "abc";
        str.indexOf("str");
        List<String> problemList = new ArrayList<>();
        String problem = br.readLine();
        while (problem != null) {
            problemList.add(problem);
            problem = br.readLine();
        }
        long seed = System.currentTimeMillis();
        Collections.shuffle(problemList, new Random(seed));

        System.out.printf("오늘 풀 문제: %s\n", problemList.get(0));

    }
}
