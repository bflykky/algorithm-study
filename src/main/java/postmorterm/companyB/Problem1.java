package main.java.postmorterm.companyB;

import java.util.Arrays;
import java.util.List;

public class Problem1 {
    public static void main(String[] args) {
        List<Integer> pnl = Arrays.asList(-3, 4, 3, -2, 2, 5);
        System.out.println("pnl: " + pnl);
        getMaxProfit(pnl, 4);
    }

    public static long getMaxProfit(List<Integer> pnl, int k) {
        int[] maxProfitToMonth = new int[pnl.size()];
        maxProfitToMonth[0] = pnl.get(0);
        for (int i = 1; i < maxProfitToMonth.length; i++) {
            maxProfitToMonth[i] = pnl.get(i) + Math.max(0, maxProfitToMonth[i - 1]);
        }

        System.out.print("[");
        for (int maxProfit : maxProfitToMonth) {
            System.out.printf("%d ", maxProfit);
        }
        System.out.print("]");
        return -1;
    }
}
