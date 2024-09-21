package main.java.postmorterm.companyB;

import java.util.Arrays;
import java.util.List;

public class Problem2 {
    public static void main(String[] args) {
        List<Integer> cost = Arrays.asList(4, 3, 9, 3, 1);
        System.out.println("cost: " + cost);
        long minimumCost = getMinimumCost(cost, 2);
        System.out.println("minimumCost: " + minimumCost);
    }

    // ith 지점까지 오는데 들은 최소비용은
    // i - k, i - (k - 1), i - (k - 2), ... i - 2, i - 1 번째 지점까지 오는 데 들은 최소비용 중,
    // 가장 작은 최소비용과 ith 지점의 비용의 합과 같다.
    // 주의. cost[1]은 1번째 지점이다. => cost[i]는 i번째 지점이다. 출발점은 0으로 원점이다.
    public static long getMinimumCost(List<Integer> cost, int k) {
        long[] minimumCostToPoint = new long[cost.size() + 1];
        minimumCostToPoint[0] = 0; // 원점을 뜻함. ith point까지 이동 시 최소합이 minimumCostToPoint[i]에 대응하도록 인덱스를 맞추기 위함이다.
        for (int i = 1; i <= k; i++) {
            minimumCostToPoint[i] = cost.get(i - 1);
        }

        for (int i = k + 1; i < minimumCostToPoint.length; i++) {
            long tmpMinimumCost = Long.MAX_VALUE;
            for (int j = k; j > 0; j--) {
                tmpMinimumCost = Math.min(tmpMinimumCost, minimumCostToPoint[i - j]);
            }
            minimumCostToPoint[i] = cost.get(i - 1) + tmpMinimumCost;
            System.out.printf("minimumCostToPoint[%d] = %d + %d = %d\n", i, cost.get(i - 1), tmpMinimumCost, minimumCostToPoint[i]);
        }
        return minimumCostToPoint[cost.size()];
    }
}
