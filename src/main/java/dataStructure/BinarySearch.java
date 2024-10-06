package main.java.dataStructure;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = new int[] {0, 1, 3, 6, 10, 15};

        System.out.println((Arrays.binarySearch(nums, 4)) );
    }

    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
