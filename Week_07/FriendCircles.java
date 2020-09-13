package org.fengt.leetcode.frequency.num_547;

import java.util.ArrayDeque;
import java.util.Deque;

public class FriendCircles {

    /**
     * Approach 1: DFS
     * Time complexity: O(N ^ 2)
     * Space complexity: O(N)
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) return 0;
        boolean[] visited = new boolean[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] m, boolean[] visited, int i) {
        for (int j = 0; j < m.length; j++) {
            if (m[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(m, visited, j);
            }
        }
    }

    /**
     * Approach 2: BFS
     * Time complexity: O(N ^ 2)
     * Space complexity: O(N)
     * @param M
     * @return
     */
    public int findCircleNum2(int[][] M) {
        if (M == null || M.length == 0) return 0;
        boolean[] visited = new boolean[M.length];
        Deque<Integer> queue = new ArrayDeque<>();
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int ii = queue.poll();
                    visited[ii] = true;
                    for (int j = 0; j < M.length; j++) {
                        if (M[ii][j] == 1 && !visited[j]) {
                            queue.offer(j);
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }

    /**
     * Approach 3: Union Find
     * Time complexity: O(N ^ 2)
     * Space complexity: O(N)
     * @param M
     * @return
     */
    public int findCircleNum3(int[][] M) {
        int n = M.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) uf.union(i, j);
            }
        }
        return uf.count();
    }

    class UnionFind {
        private int[] parent, size;
        private int count;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            count = n;
        }

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            count--;
        }

        public int count() {
            return count;
        }
    }
}
