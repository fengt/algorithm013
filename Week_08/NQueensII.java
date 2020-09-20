package org.fengt.leetcode.frequency.num_52;

import org.junit.Test;

public class NQueensII {

    @Test
    public void test() {
        System.out.println(totalNQueens2(4));
    }

    /**
     * Approach 1: Back Tracking
     * Time complexity: O(N!)
     * Space complexity: O(N)
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        boolean[] col = new boolean[n];
        boolean[] main = new boolean[2 * n - 1];
        boolean[] sub = new boolean[2 * n - 1];
        return dfs(col, main, sub, 0, n);
    }

    private int dfs(boolean[] col, boolean[] main, boolean[] sub, int row, int n) {
        int result = 0;
        if (row == n) {
            result++;
        }

        for (int i = 0; i < n; i++) {
            if (col[i] || main[i + row] || sub[i - row + n - 1]) continue;

            col[i] = true;
            main[i + row] = true;
            sub[i - row + n - 1] = true;

            result += dfs(col, main, sub, row + 1, n);

            col[i] = false;
            main[i + row] = false;
            sub[i - row + n - 1] = false;
        }

        return result;
    }

    /**
     * Approach 2: Bitmap
     * Time complexity: O(N!)
     * Space complexity: O(N)
     * @param n
     * @return
     */
    public int totalNQueens2(int n) {
        return solve(n, 0,0, 0, 0);
    }

    private int solve(int n, int row, int col, int ld, int rd) {
        int result = 0;
        if (row == n) {
            result++;
        }
        int pos = (~(col | ld | rd)) & ((1 << n) - 1);
        while (pos != 0) {
            int p = pos & (-pos);
            pos -= p; // pos &= pos - 1;
            result += solve(n, row + 1, col | p, (ld | p) << 1, (rd | p) >> 1);
        }
        return result;
    }
}
