package org.example.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {

    public static void main(String[] args) {

        int[] nums = new int[]{10,20,30,40};

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

                // Lambda expression
        names.forEach(name -> System.out.println(name));

        // Method reference (cleaner)
        names.forEach(System.out::println);

        // Different types of method references:

        // 1. Static method reference
        Function<String, Integer> parser = Integer::parseInt;
        int num = parser.apply("123"); // 123

        // 2. Instance method reference
        String str = "hello";
        Supplier<String> upperCase = str::toUpperCase;
        System.out.println(upperCase.get()); // HELLO

        // 3. Constructor reference

        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> list = listSupplier.get();

        // 4. Instance method of arbitrary object
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        words.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
