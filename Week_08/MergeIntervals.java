package org.fengt.leetcode.frequency.num_56;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    /**
     * Approach 1: Sorting
     * Time complexity: O(N * logN)
     * Space complexity: O(logN)
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval : intervals) {
            if (idx == -1 || res[idx][1] < interval[0]) {
                res[++idx] = interval;
            } else {
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }

    /**
     * Approach 1: Sorting (Optimized)
     * Time complexity: O(N * logN)
     * Space complexity: O(logN)
     * @param intervals
     * @return
     */
    public int[][] merge1(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        List<int[]> res = new ArrayList<>();
        int[] prev = intervals[0];
        res.add(prev);
        for (int[] interval : intervals) {
            if (prev[1] >= interval[0]) {
                prev[1] = Math.max(prev[1], interval[1]);
            } else {
                res.add(interval);
                prev = interval;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
