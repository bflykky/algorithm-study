package main.java.leetcode;

import java.util.Arrays;

/*
 * Sliding Window 문제. (2 Pointer라고 볼 수 있을 수도?)
 * O(n)의 시간복잡도로 푸는 게 핵심인 문제이다.
 * right 포인터가 0 ~ nums[nums.length - 1]을 반복하는 동안 찾아야 하는 값을 찾는 게 핵심이다.
 *
 * Sliding Window를 이용할 수 있는 이유
 * - subarray의 형태의 값을 찾아야 함
 *
 * nums의 요소는 모두 양수이다.
 * 만약, target보다 요소들의 합이 크거나 같은 subarray를 찾았다고 가정하자.
 * 이때, 해당 subarray에 더 요소를 추가할 필요가 없다. 목적은 가장 길이가 짧은 subarray의 길이를 찾는 거니까.
 * 즉, 현재 subarray의 요소 합 >= target이라면, 오히려 길이를 줄여봐야 한다. 이래도 돼? 이래도 돼? 하는 거지.
 * 줄이는 부분이 왼쪽이고, 늘리는 부분이 오른쪽인 것이다.
 */
public class Problem209 {
    public int minSubArrayLen(int target, int[] nums) {
        int minimumLength = nums.length + 1;
        int left = 0;
        int right = 0;

        int sum = 0;
        while (right < nums.length) {
            sum += nums[right];

            while (sum >= target) {
                minimumLength = minimumLength <= right - left + 1 ? minimumLength : right - left + 1;
                sum -= nums[left++];
            }

            right++;
        }

        return minimumLength < nums.length + 1 ? minimumLength : 0;
    }

    // binary search 방식 (O(nlogn))
    public int minSubArrayLen2(int target, int[] nums) {
        int sums[] = new int[nums.length + 1]; // nums[0] ... nums[i - 1]까지의 누적합 배열
        sums[0] = 0;
        for (int i = 1; i < sums.length; i++) {
            sums[i] = nums[i - 1] + sums[i - 1];
        }

        int minimumLength = nums.length + 1;
        for (int i = sums.length - 1; i >= 0 && sums[i] >= target; i--) {
            int j = Arrays.binarySearch(sums, sums[i] - target);
            if (j >= 0) {
                // sums[i] - target과 동일한 값이 sums에 존재해서, 해당 인덱스를 리턴한 것이다.
                // 우리가 찾는 인덱스는 sums[i] - target보다 큰 수의 인덱스이므로, ++ 증가 연산자를 수행한다.
                j++;
            } else { // 음수인 경우
                // sums[j] -  target가 존재한다면 해당 배열에 삽입되었어야 할 인덱스(insertion point)에 대해,
                // -(insertion point + 1)를 가리킨다.
                // 우리는 온전한 insertion point를 원하므로 역산을 한다.
                j = -j - 1;
            }
            minimumLength = minimumLength <= i - j + 1 ? minimumLength : i - j + 1;
        }

        return minimumLength != nums.length + 1 ? minimumLength : 0;
    }

}
