package org.example.DSA.Claude;

import java.util.Arrays;

public class ProductExceptSelf {

    /*
    Return array where each element is product of all others.
    Pattern: Left and right product arrays
     */
    public int[] productExceptSelfNaive(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int product = 1;
            // Multiply all elements except nums[i]
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }
            result[i] = product;
        }

        return result;
    }

    public int[] productExceptSelfOptimized(int[] nums){

        int n = nums.length;
        int[] result = new int[n];

        int product=1;
        int zeroCount=0;
        for(int i =0; i<n;i++){
            if(nums[i]==0) {
                zeroCount++;
            }
            else {
                product *= nums[i];
            }
        }

        for(int i=0;i<n;i++) {
            if (zeroCount > 1) {
                return result;
            } else if (zeroCount == 1) {

                result[i] = (nums[i] == 0) ? product : 0;
            } else {
                result[i] = product/nums[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {

        ProductExceptSelf p = new ProductExceptSelf();
        System.out.println(Arrays.toString(p.productExceptSelfOptimized(new int[]{0, 3, 0, 5, 6})));
    }
}
