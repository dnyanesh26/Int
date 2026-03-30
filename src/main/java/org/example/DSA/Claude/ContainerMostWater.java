package org.example.DSA.Claude;

public class ContainerMostWater {

    /*
    Find maximum water container can hold.
    Pattern: Two pointers converging from ends
     */

    public int maxAreaNaive(int[] height) {
        int maxArea = 0;

        // Try all pairs of lines
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int width = j - i;
                int minHeight = Math.min(height[i], height[j]);
                int area = width * minHeight;
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    public int maxAreaOptimized(int[] height){

        int n = height.length;
        int left =0, right = n-1;
        int maxArea =1;

        while(left<right){
            int width = right-left;
            int minHeight = Math.min(height[left],height[right]);
            maxArea = Math.max(maxArea,width*minHeight);

            if(height[left]<height[right]){
                left++;
            }else{
                right--;
            }

        }

        return maxArea;
    }

    public static void main(String[] args) {
        ContainerMostWater sol = new ContainerMostWater();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(sol.maxAreaOptimized(height)); // 49
    }
}
