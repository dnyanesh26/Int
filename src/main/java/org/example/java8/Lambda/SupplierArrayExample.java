package org.example.java8.Lambda;

import java.util.Arrays;
import java.util.function.Supplier;

public class SupplierArrayExample {
    public static void main(String[] args) {
        // Option 1: Using a lambda to create a fixed-size array
        Supplier<String[]> fixedSizeArraySupplierLambda = () -> new String[5];
        String[] lambdaArray = fixedSizeArraySupplierLambda.get();
        System.out.println("Array size created with lambda Supplier: " + lambdaArray.length);

        // Option 2: Using a helper method for the method reference
        // A method is required to match the Supplier's no-argument signature
        Supplier<String[]> fixedSizeArraySupplierRef = SupplierArrayExample::createFixedSizeStringArray;
        String[] refArray = fixedSizeArraySupplierRef.get();
        System.out.println("Array size created with method reference Supplier: " + refArray.length);
    }

    // Helper method that returns a fixed-size array, matching Supplier's get() signature
    private static String[] createFixedSizeStringArray() {
        return new String[10]; // Returns a fixed size of 10
    }
}

