package org.fengt.leetcode.frequency.num_32;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestValidParentheses {

    @Test
    public void test() {
        String s = ")(()())";
        System.out.println(longestValidParentheses(s));
    }

    /**
     * Approach 1: Stack
     * Time complexity: O(N)
     * Space complexity: O(1)
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }

    /**
     * Approach 2: DP
     * Time complexity: O(N)
     * Space complexity: O(1)
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length()];
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && s.charAt(i) == ')') {
                if (s.charAt(i - 1) == ')' && i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                } else if (s.charAt(i - 1) == '(') {
                    dp[i] = i - 2 >= 0 ? dp[i - 2] + 2 : 2;
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
