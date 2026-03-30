package org.example.DSA.Wissen;

public class SubArraySum {

    public int[] findSubarray(int[] arr, int targetSum){

        int start=0;
        int currentSum=0;
        for(int end=0;end<arr.length;end++){
            currentSum+=arr[end];
            while(currentSum>targetSum && start<=end){
                currentSum-=arr[start];
                start+=1;
            }
            if(currentSum==targetSum){
                return new int[]{start,end};
            }
        }
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        SubArraySum solution = new SubArraySum();
        int[] arr = {1, 4, 20, 3, 10, 5};
        int target = 42;
        int[] result = solution.findSubarray(arr, target);
        System.out.println("Start: " + result[0] + ", End: " + result[1]);
        // Output: Start: 2, End: 4
    }
}
