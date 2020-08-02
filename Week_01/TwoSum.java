class TwoSum{
    /**
     * Approach 1: Brute Force
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * Approach 2: Two-pass Hash Table
     * Time complexity: O(n)
     * Space complexity: O(n)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hm = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            hm.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int key = target - nums[i];
            if (hm.containsKey(key) && hm.get(key) != i) {
                return new int[] {i, hm.get(key)};
            }
        }
        return null;
    }

    /**
     * Approach 3: One-pass Hash Table
     * Time complexity: O(n)
     * Space complexity: O(1)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> hm = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int key = target - nums[i];
            if (hm.containsKey(key)) {
                return new int[] {hm.get(key), i};
            }
            hm.put(nums[i], i);
        }
        return null;
    }
}