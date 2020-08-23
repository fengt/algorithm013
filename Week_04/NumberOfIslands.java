package org.fengt.leetcode.frequency.num_200;

import java.util.ArrayDeque;
import java.util.Queue;

public class NumberOfIslands {

    /**
     * Approach 1: DFS
     * Time complexity: O(M * N)
     * Space complexity: O(M * N)
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i< 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1') return;
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    /**
     * Approach 2: BFS
     * Time complexity: O(M * N)
     * Space complexity: O(M * N)
     * @param grid
     * @return
     */
    public int numIslands2(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            i = curr[0]; j = curr[1];
            if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1') {
                grid[i][j] = '0';
                queue.add(new int[]{i - 1, j});
                queue.add(new int[]{i + 1, j});
                queue.add(new int[]{i, j - 1});
                queue.add(new int[]{i, j + 1});
            }
        }
    }
}
