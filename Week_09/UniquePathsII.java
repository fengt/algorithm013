package org.fengt.leetcode.frequency.num_63;

public class UniquePathsII {

    /**
     * Approach 1: DP
     * Time complexity: O(M * N)
     * Space complexity: O(M * N)
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];

        for (int i = 0; i < col; i++) {
            if (obstacleGrid[0][i] == 1) break;
            dp[0][i] = 1;
        }

        for (int i = 0; i < row; i++) {
            if (obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = obstacleGrid[i][j] == 0 ? dp[i - 1][j] + dp[i][j - 1] : 0;
            }
        }
        return dp[row - 1][col - 1];
    }


    /**
     * Approach 2: DP (Optimized)
     * Time complexity: O(M * N)
     * Space complexity: O(N)
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        int[] dp = new int[col + 1];
        dp[1] = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 1; j <= col; j++) {
                if (obstacleGrid[i][j - 1] == 1) dp[j] = 0;
                else dp[j] += dp[j - 1];
            }
        }
        return dp[col];
    }
}
