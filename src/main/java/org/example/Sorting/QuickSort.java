package org.example.Sorting;

import java.util.Arrays;

public class QuickSort {


    public static void sort(int[] arr,int left,int right){



        if(left<right){
            int pivot=partition(left,right,arr);
            sort(arr,left,pivot-1);
            sort(arr,pivot+1,right);
        }
    }

    private static int partition(int left, int right, int[] arr) {

        int i = left - 1;
        int j = left;

        while(j<=right){
            if(arr[j]<arr[right]){
                i++;
                if(i!=j){
                    int temp = arr[j];
                    arr[j]=arr[i];
                    arr[i]=temp;
                }
                j++;
            }
            else{
                j++;
            }
        }
        i++;
        int temp = arr[i];
        arr[i]=arr[right];
        arr[right]=temp;
        return i;
    }

    public static void main(String[] args) {

        int[] arr = new int[]{10,8,1,2,3,22,98,11,12,11};
        sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
