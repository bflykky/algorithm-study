package main.java.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Problem3043 {
    /*
     * 해당 문제에 대해 완전 탐색을 실시할 경우, 50_000 * 50_000 = 2500_000_000
     * 25억번 탐색해야 한다. => 무조건 시간 초과가 나니, 다른 방식을 찾아야 한다.
     *
     * 사용한 방식
     * 두 배열의 숫자를 뽑아 하나씩 일일이 비교하지 않는다.
     * arr1의 숫자들로 만들 수 있는 모든 접두사를 만들어 내서, Set 자료구조에 저장한다.
     * Set을 사용함으로써 중복되는 접두사들로 인한 불필요한 비교 과정을 제거한다.
     *
     * 이후, arr2에서 가능한 모든 접두사를 뽑아 해당 접두사가 set에 이미 저장되어 있는지,
     * 즉 동일한 접두사(common prefix)인지 확인한다.
     *
     * 이때, 조금이라도 불필요한 연산을 줄이기 위해, 현재까지 찾은 longestCommonPrefix보다
     * 현재 과정에서 찾을 수 있는 prefix의 길이가 작아지는 경우 반복을 종료하고 다음 수로 넘어가도록 하였다.
     * => tmpPrefixCount > result일 때만 while문 반복
     */
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> prefixSet = new HashSet<>();
        for (int num : arr1) {
            while (num > 0) {
                prefixSet.add(num);
                num /= 10;
            }
        }

        int result = 0;
        for (int num : arr2) {
            int tmpPrefixCount = Integer.toString(num).length();
            while (num > 0 && tmpPrefixCount > result) {
                if (prefixSet.contains(num)) {
                    result = tmpPrefixCount;
                }

                num /= 10;
                tmpPrefixCount--;
            }
        }
        return result;
    }
}
