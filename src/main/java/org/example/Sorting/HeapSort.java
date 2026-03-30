package org.example.Sorting;

import java.util.Arrays;

public class HeapSort {

    public static void heapify(int[] arr, int i, int n){

        int root = i;
        int lN = 2*i+1;
        int rN = 2*i+2;

        int largest = root;
        if(lN < n && arr[lN]>arr[largest])
            largest=lN;

        if(rN < n && arr[rN]>arr[largest])
            largest=rN;

        if(largest!=root){
            int temp = arr[root];
            arr[root] = arr[largest];
            arr[largest]=temp;

            heapify(arr,largest,n);
        }
    }

    public static void main(String[] args) {

        int[] arr = new int[]{10,8,1,2,3,22,98,11,12,11};

        int n=arr.length;
        for(int i=(n/2);i>=0;i--){
            heapify(arr,i,n);
        }

        for(int i=n-1; i>=0; i--){
            //last element set
            int temp=arr[i];
            arr[i]=arr[0];
            arr[0]=temp;
            heapify(arr,0,i);
        }

        System.out.println(Arrays.toString(arr));
    }

}
