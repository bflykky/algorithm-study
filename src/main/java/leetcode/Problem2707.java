package main.java.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Problem2707 {

    /*
     * DP를 이용한 풀이
     * 예시를 들자.
     * "babocodeho" 라는 문자열 s가 주어지고, ["bab", "code"] 라는 ditionary가 주어졌다.
     * 해당 DP는, 부분 문자열 s[i:]에 대한 minExtraChar를 찾는 부분문제로 나눠 풀이한 것이다.
     * 우측 끝부터 시작한 부분문자열을 점차 키워나가면서, 마지막에는 전체 문자열에 대한 minExtraChar를 찾는 방식이다.
     *
     * 과정을 살펴보자.
     * 0. dp[s.length()] = 0으로 초기화된 값 그대로 사용한다.
     * 1. 첫 번째 부분 문자열 "o": "o"라는 단어가 사전에 존재하지 않으므로, dp[9] = 1이다.
     * 2. 두 번째 부분 문자열 "ho": dp[8] = 1 + dp[9] = 2이다. 당연히 이전 부분 문자열의 minExtraChar이 1이었고,
     * 인덱스를 왼쪽으로 1 옮기면서 늘어난 만큼 문자가 하나 더 늘어났으므로 extra char라고 판단하고 1을 더하는 것이다.
     * "ho"라는 문자열도 없으므로 다음으로 넘어간다.
     * ... 이를 쭉 반복하면, 순서대로 1씩 늘어나 dp[5] = 5, dp[6] = 4, dp[7] = 3, dp[8] = 2, dp[9] = 1 가 된다.
     *
     * 6. 여섯 번째 부분 문자열 "codeho": 일단 사전에 나타난 문자열이 없다 판단해서 1 + dp[5] = 6으로 초기화한다.
     * 하지만, "code"라는 단어가 dictionary에 존재한다. 따라서 if문 내부를 수행하는데,
     * !! 단순히 값을 줄이는 게 아니라, dp[4 + "code"의 문자열 길이] = dp[8]을 Math.min()의 2번째 파라미터로 넣어 값을 구한다.
     *
     * ====
     * 왜 그럴까? 만약, dp[8]에 대한 부분 문자열에서도 사전에 나온 단어가 있었다면, dp[8]은 해당 단어를 제외해 extr char를 계산해 놨을 것이다.
     * 단순히 "code"의 길이 4만큼 빼버리면, 이전에 해놓은 계산을 무시하는 것이다.
     * 따라서, dp[4]는 "code"라는 문자열을 포함한다.
     * => dp[4]는 dp[4]에 대한 부분 문자열에서 "code"를 뺀 부분 문자열에 대해 min extra char를 계산한 값과 동일하다.
     * => "codeho"에 대한 min Extra char는 "codeho" - "code" = "ho", "ho"에 대한 min Extra char를 구한 값과 동일하다.
     * => dp[4] = Math.min(dp[4], dp[4 + "code".length()]) 라는 식이 되는 것이다.
     *
     *
     * ===
     * 또 다른 궁금증
     * 1. 현재 부분 문자열에 대해 포함하는 word가 있기 판단하기 전, // "tmpIndex <= s.length()" 조건식을 거치는 이유는 뭘까?
     * 코드 상 부분문자열을 만들어 놓고 contains() 함수를 쓰는 게 아닌, 현재 반복하는 단어의 길이 만큼 substring()을 하기 때문에,
     * "현재 인덱스 + 특정 단어의 길이"가 s의 마지막 인덱스를 넘어버리면 StringIndexOutOfBoundsException 예외가 발생한다.
     * 이를 방지하기 위해 앞에 하나의 조건식이 있는 것이다.
     *
     * 2. Math.min()으로 dp[i]를 초기화하는 이유는 뭘까?
     * "scodesho"라는 부분 문자열이고, 사전은 ["code, "codesho", "ho"] 라고 하자.
     *
     * 사전의 3개 단어 모두 해당 부분 문자열에 포함되기에 if문 내부를 실행한다.
     * 그때마다 업데이트된다면, 마지막 사전 단어가 무엇이냐에 따라 값이 달라진다.
     * 게다가 해당 값이 가장 최소의 min extra char인지도 모른다.
     * 실제로 "codesho" 단어를 이용했을 때가 dp[i] = 1로 가장 작아진다.
     * 따라서, 여러 개의 단어가 포함되어 if문이 여러 번 실행되어도, 최소의 값이 들어갈 수 있도록
     * Math.min()이 사용된 것이다.
     */
    public int minExtraChar(String s, String[] dictionary) {
        int[] dp = new int[s.length() + 1];

        // 실제로 문자열에서 나타나는 단어만 추출
        // 없어도 되는 로직이긴 하지만, 아래 반복문에서 유효한 단어만 반복하기 위해 추가
        Set<String> validDictionary = new HashSet<>();
        for (String word : dictionary) {
            if (s.contains(word)) {
                validDictionary.add(word);
            }
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i] = 1 + dp[i + 1];

            for (String word : validDictionary) {
                int tmpIndex = i + word.length();
                if (tmpIndex <= s.length() && s.substring(i, tmpIndex).equals(word)) {
                    dp[i] = Math.min(dp[i], dp[tmpIndex]);
                }
            }
        }

        return dp[0];
    }
}
