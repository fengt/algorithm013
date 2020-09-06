package org.fengt.leetcode.frequency.num_64;

public class MinimumPathSum {

    /**
     * DP
     * Time complexity: O(M * N)
     * Space complexity: O(M * N)
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[i][j] = grid[i][j];
                if (i == 0 && j == 0) continue;

                if (i == 0) dp[i][j] += dp[i][j - 1];
                else if (j == 0) dp[i][j] += dp[i - 1][j];
                else dp[i][j] += Math.min(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[row - 1][col - 1];
    }
}
