package org.example.DSA.Wissen;

public class RainWaterTrapping {
    public static int trap(int[] height) {
        if (height == null || height.length == 0) return 0;

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while (left <= right) {
            if (leftMax <= rightMax) {
                leftMax = Math.max(leftMax, height[left]);
                water += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                water += rightMax - height[right];
                right--;
            }
        }
        return water;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 1, 0, 1, 0, 1};
        System.out.println("Water trapped: " + trap(arr));
    }
}


