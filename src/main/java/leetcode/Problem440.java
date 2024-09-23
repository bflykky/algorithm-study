package main.java.leetcode;

public class Problem440 {
    public int findKthNumber(int n, int k) {
        int result = 1;

        k--; // 첫번째 요소를 채웠으니 1 감소
        while (k > 0) {
            int req = getReqNum(result, n);
            if (k >= req) { // k번째 수가 하위 노드에 없는 경우 => 다음 형제 노드로 이동 (+ 1)
                k -= req;
                result++;
            } else { // k번째 수가 하위 노드에 있는 경우 => 자식 노드 중 맨 왼쪽 노드로 이동 (* 10)
                k--;
                result *= 10;
            }
        }

        return result;
    }

    /*
     * Solutions을 보면서 든 2가지 고민
     * 1. 왜 Math.min()에서 upperBound + 1 일까?
     * 상계에 제한되지 않는다면, 80 - 70 = 10, 50 - 40 = 10, ... 100 - 90 = 10 처럼 10개씩 count한다.
     * 하지만, 상계가 15라면? 15 - 10 = 5라는 값이 나온다. 사실은 [10, 11, 12, 13, 14, 15]로 가능한 숫자들은
     * 6개인데 말이다. 그래서 upperBound가 아닌 upperBound + 1이다.
     *
     * => 정리하면, 70부터 79까지, 40부터 49까지, 90부터 99까지 모두 10개이므로 10개씩 count하기 위해선 단순히
     * siblingNode - n을 하면 된다.
     * 하지만 upperBound가 해당 10개의 숫자 중 하나라면, '[a, b] 범위의 정수 개수' = b - a + 1 = (b + 1) - a 이다.
     * b + 1에 대응하도록 upperBound + 1이 파라미터로 전달된다.
     *
     * 2. 왜 n과 siblingNode의 타입이 long일까?
     * 문제 조건 상 주어지는 n은 10억으로 int 타입으로 해결할 수 있다. 하지만, 해당 함수에 전달되는 n의 최대 크기는
     * 999_999_990일 것이다. 이는 int 값이긴 하다만, 함수 내부 로직에서 * 10 연산을 하면 오버플로우가 발생해 값이 기대한 값이
     * 아니게 된다. 따라서, 약 10억의 * 10인 100억도 감당할 수 있도록 long 타입을 써야 한다.
     */
    private int getReqNum(long n, int upperBound) {
        int count = 0; // 1 + 10 = 11
        long siblingNode = n + 1;
        while (n <= upperBound) {
            count += Math.min(upperBound + 1, siblingNode) - n;
            n *= 10;
            siblingNode *= 10;
        }

        return count;
    }
}
