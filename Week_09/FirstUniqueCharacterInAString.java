package org.fengt.leetcode.frequency.num_387;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharacterInAString {

    /**
     * Counter
     * Time complexity: O(N)
     * Space complexity: O(N)
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (freq.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}
