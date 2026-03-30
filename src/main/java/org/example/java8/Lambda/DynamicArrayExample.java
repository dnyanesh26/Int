package org.example.java8.Lambda;

import java.util.function.Function;

public class DynamicArrayExample {
    public static void main(String[] args) {
        // The array constructor reference String[]::new matches the Function<Integer, String[]> signature
        Function<Integer, String[]> dynamicArrayCreator = String[]::new;

        String[] dynamicArray = dynamicArrayCreator.apply(3); // Creates an array of size 3
        System.out.println("Dynamic array size: " + dynamicArray.length);

        String[] anotherArray = dynamicArrayCreator.apply(7); // Creates an array of size 7
        System.out.println("Another dynamic array size: " + anotherArray.length);
    }
}

