package org.fengt.leetcode.frequency.num_51;

import java.util.*;

public class NQueens {

    private Set<Integer> col = new HashSet<>();
    private Set<Integer> main = new HashSet<>();
    private Set<Integer> sub = new HashSet<>();

    /**
     * Back Tracking
     * Time complexity: O(N!)
     * Space complexity: O(N)
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), 0, n);
        return res;
    }

    private void dfs(List<List<String>> res, ArrayList<String> path, int row, int n) {
        if (row == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (col.contains(i) || main.contains(row + i) || sub.contains(row - i)) continue;

            char[] temp = new char[n];
            Arrays.fill(temp, '.');
            temp[i] = 'Q';
            path.add(new String(temp));

            col.add(i);
            main.add(row + i);
            sub.add(row - i);

            dfs(res, path, row + 1, n);

            path.remove(path.size() - 1);
            col.remove(i);
            main.remove(row + i);
            sub.remove(row - i);
        }
    }

    /**
     * Back Tracking (Optimized by boolean)
     * 1. HashSet: 15ms, beats 17.85%
     * 2. Boolean Array: 5ms, beats 91.44%
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> res = new ArrayList<>();
        boolean[] col = new boolean[n];
        boolean[] main = new boolean[2 * n - 1];
        boolean[] sub = new boolean[2 * n - 1];
        dfs(res, new ArrayList<>(), col, main, sub, 0, n);
        return res;
    }

    private void dfs(List<List<String>> res, ArrayList<String> path,
                     boolean[] col, boolean[] main, boolean[] sub, int row, int n) {
        if (row == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (col[i] || main[row + i] || sub[row - i + n - 1]) continue;

            char[] temp = new char[n];
            Arrays.fill(temp, '.');
            temp[i] = 'Q';
            path.add(new String(temp));

            col[i] = true;
            main[row + i] = true;
            sub[row - i + n - 1] = true;

            dfs(res, path, col, main, sub, row + 1, n);

            path.remove(path.size() - 1);
            col[i] = false;
            main[row + i] = false;
            sub[row - i + n - 1] = false;
        }
    }
}
