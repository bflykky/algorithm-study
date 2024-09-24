package main.java.leetcode;

import java.util.Arrays;

public class Problem88 {
    /*
     * 리트코드 처음 접할 때 풀었던 문제 재풀이.
     * 그때의 기억을 어슴풋이 떠올려, 잘 풀어내었다.
     * 2개의 포인터를 이용해 사용한 값이 들어있는 배열의 포인터만 값을 늘려가면서
     * 총 시간 복잡도 O(m + n)으로 풀이하였다.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] tmpNums = Arrays.copyOf(nums1, m);
        int idx = 0;
        int pointer1 = 0;
        int pointer2 = 0;
        while (pointer1 < m && pointer2 < n) {
            nums1[idx++] = tmpNums[pointer1] <= nums2[pointer2] ? tmpNums[pointer1++] : nums2[pointer2++];
        }

        while (pointer1 < m) {
            nums1[idx++] = tmpNums[pointer1++];
        }

        while (pointer2 < n) {
            nums1[idx++] = nums2[pointer2++];
        }

        return;
    }
}