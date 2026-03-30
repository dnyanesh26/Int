package org.example.java8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForEach {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange");

// Traditional for loop
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

// Java 8 forEach
        fruits.forEach(fruit -> System.out.println(fruit));

// With method reference
        fruits.forEach(System.out::println);

// Map forEach
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 5);
        map.put("Banana", 3);
        map.put("Orange", 7);

        map.forEach((key, value) ->
                System.out.println(key + " = " + value));
    }
}
