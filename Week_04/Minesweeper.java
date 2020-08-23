package org.fengt.leetcode.frequency.num_529;

import java.util.ArrayDeque;
import java.util.Deque;

public class Minesweeper {

    private static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    /**
     * Approach 1: DFS
     * Time complexity: O(M * N)
     * Space complexity: O(M * N)
     * @param board
     * @param click
     * @return
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board.length == 0 || board[0].length == 0 || click.length != 2) return board;
        int m = board.length, n = board[0].length;
        int row = click[0], col = click[1];

        if (board[row][col] == 'M') {
            board[row][col] = 'X';
        } else {
            dfs(board, row, col, m, n);
        }
        return board;
    }
    private void dfs(char[][] board, int row, int col, int m, int n) {
        int cnt = 0;
        for (int[] dir: dirs) {
            int x = row + dir[0], y = col + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n) continue;
            if (board[x][y] == 'M') cnt++;
        }

        if (cnt > 0) {
            board[row][col] = (char) (cnt + '0');
            return;
        } else {
            board[row][col] = 'B';
            for (int[] dir: dirs) {
                int x = row + dir[0], y = col + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n) continue;
                if (board[x][y] == 'E') dfs(board, x, y, m, n);
            }
        }
    }

    /**
     * Approach 2: BFS
     * Time complexity: O(M * N)
     * Space complexity: O(M * N)
     * @param board
     * @param click
     * @return
     */
    public char[][] updateBoard2(char[][] board, int[] click) {
        if (board.length == 0 || board[0].length == 0 || click.length != 2) return board;
        int m = board.length, n = board[0].length;

        Deque<int[]> deque = new ArrayDeque<>();
        deque.push(click);
        while (!deque.isEmpty()) {
            int[] cell = deque.pollLast();
            int row = cell[0], col = cell[1];

            if (board[row][col] == 'M') {
                board[row][col] = 'X';
            } else {
                int cnt = 0;
                for (int[] dir: dirs) {
                    int x = row + dir[0], y = col + dir[1];
                    if (x < 0 || x >= m || y < 0 || y >= n) continue;
                    if (board[x][y] == 'M') {
                        cnt++;
                    }
                }

                if (cnt > 0) {
                    board[row][col] = (char) (cnt + '0');
                } else {
                    board[row][col] = 'B';
                    for (int[] dir: dirs) {
                        int x = row + dir[0], y = col + dir[1];
                        if (x < 0 || x >= m || y < 0 || y >= n) continue;
                        if (board[x][y] == 'E') {
                            deque.push(new int[]{x, y});
                            board[x][y] = 'B'; // Avoid to be added again
                        }
                    }
                }
            }
        }
        return board;
    }
}
