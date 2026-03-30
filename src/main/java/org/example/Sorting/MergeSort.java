package org.example.Sorting;

import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] arr,int left, int right){
        int mid = (left + right) / 2;
        if(left<right) {
            mergeSort(arr,left,mid);
            mergeSort(arr,mid+1,right);
        }

            merge(arr,left,mid,right);
    }

    public static void merge(int[] arr, int left, int mid, int right){

        int n1=mid-left+1;
        int n2=right-mid;
        int[] lA = new int[n1];
        int[] rA = new int[n2];



        for(int a=0;a<n1;a++){
            lA[a]=arr[a+left];
        }
        for(int b=0;b<n2;b++){
            rA[b]=arr[b+mid+1];
        }
        int i=0;
        int j=0;
        int k = left;

        while(i<n1 && j<n2){

            if(lA[i]<=rA[j]){
                arr[k]=lA[i];
                i++;
                k++;
            }else{
                arr[k]=rA[j];
                j++;
                k++;
            }
        }

        while(i<n1){
            arr[k]=lA[i];
            i++;
            k++;
        }
        while(j<n2){
            arr[k]=rA[j];
            j++;
            k++;
        }

    }

    public static void main(String[] args) {

        int[] arr = new int[]{10,8,1,2,3,22,98,11,12,11};
        mergeSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
