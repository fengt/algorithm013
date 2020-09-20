package org.fengt.leetcode.frequency.num_190;

public class ReverseBits {
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += (n & 1) << (31 - i);
            n >>= 1;
        }
        return res;
    }
}
