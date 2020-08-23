package org.fengt.leetcode.frequency.num_122;

public class BestTimeToBuyAndSellStockII {

    /**
     * Approach 1: Brute Force
     * Time complexity: O(N^N)
     * Space complexity: O(N)
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        return calculate(prices, 0);
    }

    private int calculate(int[] prices, int start) {
        if (start >= prices.length) return 0;
        int max = 0;
        for (int i = start; i < prices.length; i++) {
            int maxProf = 0;
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    int profit = calculate(prices, j + 1) + prices[j] - prices[i];
                    if (profit > maxProf) {
                        maxProf = profit;
                    }
                }
            }
            if (maxProf > max) max = maxProf;
        }
        return max;
    }

    /**
     * Approach 2: Peak Valley Approach
     * Time complexity: O(N)
     * Space complexity: O(1)
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int i = 0, max = 0, len = prices.length;
        int valley = prices[0];
        int peek = prices[0];
        while (i < len - 1) {
            while (i < len - 1 && prices[i] >= prices[i + 1]) i++;
            valley = prices[i];
            while (i < len - 1 && prices[i] <= prices[i + 1]) i++;
            peek = prices[i];
            max += peek - valley;
        }
        return max;
    }

    /**
     * Approach 3: Simple One Pass
     * Time complexity: O(N)
     * Space complexity: O(1)
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            if (diff > 0) max += diff;
        }
        return max;
    }
}
