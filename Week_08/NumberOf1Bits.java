package org.fengt.leetcode.frequency.num_191;

import org.junit.Test;

public class NumberOf1Bits {

    @Test
    public void test() {
        System.out.println(hammingWeight(7));
    }

    /**
     * Approach 1: Loop 32 bits
     * Time complexity: O(1)
     * Space complexity: O(1)
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int bits = 0, mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) bits++;
            mask <<= 1;
        }
        return bits;
    }

    /**
     * Approach 2: Bit Manipulation Trick
     * Time complexity: O(1)
     * Space complexity: O(1)
     * @param n
     * @return
     */
    public int hammingWeight2(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }
}
