package org.fengt.leetcode.frequency.num_32;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestValidParentheses {

    /**
     * Approach 1: Stack
     * Time complexity: O(N)
     * Space complexity: O(N)
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

    public int longestValidParentheses11(String s) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push(i);
            else if (!stack.empty() && s.charAt(stack.peek()) == '(') stack.pop();
            else stack.push(i);
        }
        if (stack.empty()) return s.length();
        int res = 0, high = s.length();

        while (!stack.empty()) {
            int low = stack.pop();
            res = Math.max(res, high - low - 1);
            high = low;
        }
        return Math.max(res, high);
    }

    /**
     * Approach 2: DP
     * Time complexity: O(N)
     * Space complexity: O(N)
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

    /**
     * Approach 3: Two Counter
     * Time complexity: O(N)
     * Space complexity: O(1)
     * @param s
     * @return
     */
    public int longestValidParentheses3(String s) {
        int left = 0, right = 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                res = Math.max(res, 2 * left);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                res = Math.max(res, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return res;
    }
}
