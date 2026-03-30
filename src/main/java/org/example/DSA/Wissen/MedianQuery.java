package org.example.DSA.Wissen;

import java.util.Arrays;

public class MedianQuery {
    public static int findMedian(int[] arr, int L, int R) {
        // Convert to 0-indexed
        L = L - 1;
        R = R - 1;

        // Extract and copy subarray
        int length = R - L + 1;
        int[] subarray = new int[length];

        for (int i = 0; i < length; i++) {
            subarray[i] = arr[L + i];
        }

        // Sort the subarray
        Arrays.sort(subarray);

        // Find median
        if (length % 2 == 1) {
            // Odd length: middle element
            return subarray[(length-1) / 2];
        } else {
            // Even length: smaller of two middle elements
            return subarray[(length-1) / 2];
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 1, 9, 2};

        // Query 1: L=2, R=5 (1-indexed)
        System.out.println(findMedian(arr, 2, 5)); // Output: 3

        // Query 2: L=1, R=3 (1-indexed)
        System.out.println(findMedian(arr, 1, 3)); // Output: 5
    }
}

