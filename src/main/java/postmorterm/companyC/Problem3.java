package main.java.postmorterm.companyC;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Problem3 {
    public static void main(String[] args) {
        int[] test = {18, 24, 22, 48};
        System.out.println(solution(test));
    }

    public static long solution(int[] weights) {
        long answer = 0;
        int currentWeight = 0; // 현재 껴있는 바벨 무게
        int biggestPlate = 0; // 현재 껴있는 가장 무거운 원판
        Stack<Integer> plateStack = new Stack<>();
        for (int weight : weights) {
            // 원판을 빼는 로직
            List<Integer> plateList = getPlateList(weight / 2);// 목표 무게에 필요한 원판 리스트 get
            if (plateList.get(0) > biggestPlate) { // 목표 무게에 필요한 원판 중 가장 무거운 게 현재 가장 무거운 원판보다 무거운 경우
                // 원판 다 빼
                while (!plateStack.empty()) {
                    int plate = plateStack.pop();
                    currentWeight -= plate;
                    answer += plate;
                }
                biggestPlate = 0;
            }

            // 현재 껴있는 원판보다 목표 무게가 낮은 경우, 아까 구한 원판의 subList(0, ~)가 되도록 원판을 뺀다.
            while (!plateStack.empty() && plateStack.peek() <= Integer.highestOneBit(currentWeight ^ weight / 2)) {
                int plate = plateStack.pop();
                currentWeight -= plate;
                answer += plate;
            }

            // 현재 껴잇는 무게가 목표 무게와 동일해질 때까지 진행한다
            for (int plate : plateList) {
                if (plate > weight / 2 - currentWeight) {
                    continue;
                }
                if (currentWeight == 0) { // 현재 무게가 0이라면 가장 무거운 원판을 update한다
                    biggestPlate = plate;
                }
                plateStack.push(plate);
                currentWeight += plate;
                answer += plate;
            }
        }

        return answer * 2;
    }

    public static List<Integer> getPlateList(int targetWeight) {
        List<Integer> result = new ArrayList<>();
        while (targetWeight > 0) {
            int plate = Integer.highestOneBit(targetWeight);
            result.add(plate);
            targetWeight -= plate;
        }

        return result;
    }
}
