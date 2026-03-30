package org.example.DSA.Claude;

import java.util.*;

public class Sum3 {

    /*
    Find all unique triplets that sum to zero.
    Pattern: Sort + Two pointers
     */

    public List<List<Integer>> threeSumNaive(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet<>();

        // Try all triplets
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(triplet);
                        resultSet.add(triplet);
                    }
                }
            }
        }

        return new ArrayList<>(resultSet);
    }

    public List<List<Integer>> threeSumOptimized(int[] nums){

        List<List<Integer>> result=new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            if(i>0 && nums[i] == nums[i-1])
                continue;

            int left = i+1, right = nums.length-1, target = -nums[i];


            while (left < right) {
                int sum = nums[left] + nums[right];
                if(sum==target) {

                    if (left < right && nums[left] == nums[left + 1])
                        left++;
                    if (left < right && nums[right] == nums[right - 1])
                        right--;
                    if (nums[left] + nums[right] == target) {
//                        System.out.println(nums[i] + "" + "" + nums[left] + "" + "" + nums[right]);
                        result.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));

                    }
                    left++;
                    right--;

                } else if (sum<target) {
                    left++;

                }else {
                    right--;
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        Sum3 sol = new Sum3();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(sol.threeSumOptimized(nums));
        // [[-1, -1, 2], [-1, 0, 1]]
    }
}
