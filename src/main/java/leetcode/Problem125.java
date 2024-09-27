package main.java.leetcode;

public class Problem125 {
    /*
     * 2개의 포인터를 이용한 방식
     * alphanumeric인지 판단 후, 아니라면 해당 문자 건너뛰기(++, -- 연산자 이용)
     * alphanumeric인 경우, 2개의 포인터가 가리키는 문자가 동일한지 비교
     */
    public boolean isPalindrome(String s) {
        int pointer1 = 0;
        int pointer2 = s.length() - 1;
        while (pointer1 < pointer2) {
            char c1 = s.charAt(pointer1);
            if (!isAlphanumeric(c1)) {
                pointer1++;
                continue;
            }

            char c2 = s.charAt(pointer2);
            if (!isAlphanumeric(c2)) {
                pointer2--;
                continue;
            }

            if (Character.toLowerCase(c1) == Character.toLowerCase(c2)) {
                pointer1++;
                pointer2--;
            } else {
                return false;
            }
        }

        return true;
    }


    public boolean isAlphanumeric(char c) {
        // 삼항 연산자 안하고 그냥 해당 연산식을 리턴하면 되는 거였어.
        // return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'); 처럼.
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')
                ? true : false;
    }
}