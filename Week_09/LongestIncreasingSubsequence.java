package org.fengt.leetcode.frequency.num_300;

public class LongestIncreasingSubsequence {

    /**
     * Approach 1: DP
     * Time complexity: O(N ^ 2)
     * Space complexity: O(N)
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * Approach 2: DP with Binary Search
     * Time complexity: O(N * logN)
     * Space complexity: O(N)
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int num : nums) {
            int i = 0, j = size;
            while (i < j) {
                int k = (j - i) / 2 + i;
                if (tails[k] < num) {
                    i = k + 1;
                } else {
                    j = k;
                }
            }
            tails[i] = num;
            if (i == size) size++;
        }
        return size;
    }
}
