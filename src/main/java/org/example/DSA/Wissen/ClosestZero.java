package org.example.DSA.Wissen;

public class ClosestZero {

    public static int findClosest(int[] nums){

        int best=nums[0];
        for(int i=0;i<nums.length;i++){

            int absCurrent = Math.abs(nums[i]);
            int absBest = Math.abs(best);
            int current = nums[i];

            if(absCurrent<absBest){
                best=current;

            } else if (absCurrent==absBest) {
                if(current>best){
                    best=current;
                }

            }
        }
        return best;
    }

    public static void main(String[] args) {
        int[] arr1 = {-4, -2, 1, 4, 8};
        System.out.println(findClosest(arr1)); // Output: 1

        int[] arr2 = {2, -1, 1};
        System.out.println(findClosest(arr2)); // Output: 1

        int[] arr3 = {-5, -3, 3, 5};
        System.out.println(findClosest(arr3)); // Output: 3

    }
}
