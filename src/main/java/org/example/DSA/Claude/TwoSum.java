package org.example.DSA.Claude;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
 /*
    Find two numbers that add up to target.
    Pattern: Hash map to store complements
 */

    /*
    Time Complexity: O(n²) - nested loops
    Space Complexity: O(1) - no extra space
    Approach: Check every possible pair until we find the target sum
    */
    public int[] twoSumNaive(int[] nums, int target) {
        // Brute force: Check every pair
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }


    /*
    Time Complexity: O(n) - single pass through array
    Space Complexity: O(n) - hash map storage
    Pattern: Hash map for complement lookup
    Key Insight: Store each number as we go, check if complement exists
    */
    public int[] twoSumOptimized(int[] nums, int target) {
        // Hash map to store number and its index
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);
        }

        return new int[]{};
    }

    public static void main(String[] args) {
        TwoSum sol = new TwoSum();
        int[] nums = {2, 7, 11, 15};
        int[] result = sol.twoSumOptimized(nums, 9);
        System.out.println("[" + result[0] + ", " + result[1] + "]"); // [0, 1]
    }
}
