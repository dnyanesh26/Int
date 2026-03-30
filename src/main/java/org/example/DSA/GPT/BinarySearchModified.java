package org.example.DSA.GPT;

public class BinarySearchModified {

    public int search(int[] arr, int n){

        int left=0,right=arr.length-1;

        while(left<=right){

            int mid = (left+right)/2;
            if(arr[mid]==n)
                return mid;

            if(arr[left]<=arr[mid]){
                if(n>=arr[left] && n<arr[mid])
                    right=mid-1;
                else
                    left=mid+1;
            }else{
                if(n>arr[mid] && n<=arr[right])
                    left=mid+1;
                else
                    right=mid-1;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        BinarySearchModified sol = new BinarySearchModified();
        System.out.println(sol.search(new int[]{4,5,6,7,0,1,2}, 0)); // Output: 4
        System.out.println(sol.search(new int[]{1}, 0));           // Output: -1
        System.out.println(sol.search(new int[]{3, 1}, 1));        // Output: 1
    }
}
