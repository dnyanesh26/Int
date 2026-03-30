package org.example.DSA.Wissen;

import java.util.Arrays;

public class MoveZeros {

    public void move(int[] arr){

        int i=-1, j=0;
        while(j<arr.length){
            if(arr[j]!=0){
                i+=1;
                if(i!=j){
                    int temp=arr[j];
                    arr[j]=arr[i];
                    arr[i]=temp;
                }
                j+=1;
            }
            else{
                j+=1;
            }
        }
    }
    public static void main(String[] args) {
        MoveZeros solution = new MoveZeros();
        int[] arr = {0, 1, 0, 3, 12};
        solution.move(arr);
        System.out.println(Arrays.toString(arr));
        // Output: [1, 3, 12, 0, 0]
    }
}
