package main.java.leetcode;

public class Problem14 {
    // 통상적인 방식. 완전 탐색
    public String longestCommonPrefix(String[] strs) {
        // 완전 탐색: 200 * 200 = 40_000번
        int longestPrefixLen = 0;
        boolean flag = false;
        while (longestPrefixLen < strs[0].length() && !flag) {
            char c = strs[0].charAt(longestPrefixLen);
            for (String str : strs) {
                if (longestPrefixLen >= str.length() || c != str.charAt(longestPrefixLen)) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                break;
            }
            longestPrefixLen++;
        }

        return strs[0].substring(0, longestPrefixLen);
    }


    // binary search를 사용한 풀이 O(n * log S) n: strs의 길이, S: 가장 짧은 문자열의 길이
    public String longestCommonPrefix2(String[] strs) {
        String shortestStr = strs[0];
        for (String str : strs) {
            if (str.length() < shortestStr.length()) {
                shortestStr = str;
            }
        }

        int left = 1;
        int right = shortestStr.length();
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isCommonPrefix(strs, shortestStr.substring(0, mid))) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return shortestStr.substring(0, (left + right) / 2);
    }

    public boolean isCommonPrefix(String[] strs, String prefix) {
        for (String str : strs) {
            if (!str.startsWith(prefix)) {
                return false;
            }
        }

        return true;
    }
}
