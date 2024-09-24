package main.java.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem1 {
    /*
     * 완전 탐색 시 시간 복잡도는 O(n^2)이다.
     * 이보다 작은 시간복잡도로 푸는 방식은?
     *
     * => <요소 값, 요소가 위치한 인덱스> 해시맵을 이용하는 방식.
     * 반복문으로 nums 배열을 돌아가면서 "target - 현재 요소"와 동일한 값이 있는지 판단하는 게 아니라
     * hash table을 이용해 O(1)에 가까운 복잡도로 빠르게 찾는다.
     * 해당 로직은 총 O(n)의 시간 복잡도이다.
     *
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complementNum = target - nums[i];
            if (numMap.containsKey(complementNum)) {
                // 리턴값 [a, b]는 무조건! a < b를 만족한다. (답에 상관은 없지만, 코드가 어떻게 굴러가는지는 알아야 하니까.)
                return new int[] {numMap.get(complementNum), i};
            }
            numMap.put(nums[i], i);
        }

        return new int[] {};
    }
}