package org.example.Sorting;

import java.util.Arrays;

public class InsertionSort {

    public static void sort(int[] arr){

        for(int i=1;i<arr.length;i++){

            int card = arr[i];
            int j=i-1;
            while(j>=0 && card<arr[j]){
                arr[j+1]=arr[j];
                j-=1;
            }
            arr[j+1]=card;
        }
    }

    public static void main(String[] args) {

        int[] arr = new int[]{10,8,1,2,3,22,98,11,12,11};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
