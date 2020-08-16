package org.fengt.leetcode.frequency.num_77;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    /**
     * Approach 1: Back Track
     * Time complexity: O(C(n, k)*k)
     * Space complexity: O(C(n, k))
     * @param n
     * @param k
     * @return
     */
    private List<List<Integer>> res;
    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        backTracking(1, n, k, new ArrayList<>());
        return res;
    }

    private void backTracking(int start, int n, int k, List<Integer> curr) {
        if (k == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i <= n - k + 1; i++) {
            curr.add(i);
            backTracking(i + 1, n, k - 1, curr);
            curr.remove(curr.size() - 1);
        }
    }

    /**
     * Approach 2: Iterative
     * Time complexity: O(C(n, k)*k)
     * Space complexity: O(k)
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>(k);
        int i = 0;
        while (i < k) {
            temp.add(0);
            i++;
        }

        int j = 0;
        while (j >= 0) {
            temp.set(j, temp.get(j) + 1);
            if (temp.get(j) > n) {
                j--;
            } else if (j == k - 1) {
                res.add(new ArrayList<>(temp));
            } else {
                j++;
                temp.set(j, temp.get(j - 1));
            }
        }
        return res;
    }
}
