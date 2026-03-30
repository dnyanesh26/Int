package org.example.java8.Streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTerminalOthers {

    public static void main(String[] args) {

        //count() — returns number of elements
        List<Integer> numbers = Arrays.asList(2, 8, 4, 10, 1, 7);
        // Filter numbers > 5 and count them
        long count = numbers.stream()
                .filter(n -> n > 5)
                .count();
        System.out.println("Count of numbers > 5: " + count);
        // Output: Count of numbers > 5: 3
        
        //findFirst() — returns Optional of first element
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob", "Amanda");
        // Find the first name starting with "A"
        Optional<String> first = names.stream()
                .filter(s -> s.startsWith("A"))
                .findFirst();
        // Safe ways to handle the result
        // 1. Using ifPresent (only runs if a match was found)
        first.ifPresent(name -> System.out.println("Found: " + name)); // Output: Alice
        // 2. Using orElse (provides a default value)
        String result = first.orElse("No match found");
        System.out.println(result); // Output: Alice
        
        //findAny() — returns Optional of any element (faster in parallel)
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry", "Date");
        // Find any element that contains 'a'
        Optional<String> anyResult = fruits.stream()
                .filter(s -> s.contains("a")).peek(System.out::print)
                .findAny();
        // Handle the Optional result
        if (anyResult.isPresent()) {
            System.out.println("Found one: " + anyResult.get());
        } else {
            System.out.println("No matching element found.");
        }
        
        //anyMatch() — returns true if any element matches predicate
        List<String> messages = Arrays.asList("hi there", "hello world", "good morning");
        // Returns true if any string contains "hello"
        boolean result1 = messages.stream()
                .anyMatch(s -> s.contains("hello"));
        if (result1) {
            System.out.println("The list contains the keyword!");
        } else {
            System.out.println("Keyword not found.");
        }
        
        //allMatch() — returns true if all elements match predicate
        List<Integer> numbers1 = Arrays.asList(10, 5, 8, 20, 1);
        // Check if ALL numbers are greater than 0
        boolean allPositive = numbers1.stream()
                .allMatch(n -> n > 0);
        System.out.println("Are all numbers positive? " + allPositive);
        // Output: Are all numbers positive? true
        List<Integer> mixedNumbers = Arrays.asList(10, -5, 8);
        boolean allPositiveMixed = mixedNumbers.stream()
                .allMatch(n -> n > 0);
        System.out.println("Are all mixed numbers positive? " + allPositiveMixed);
        // Output: Are all mixed numbers positive? false
        
        //noneMatch() — returns true if no elements match predicate
        List<Integer> prices = Arrays.asList(100, 250, 50, 99);
        // Check if NONE of the prices are negative
        boolean noNegatives = prices.stream()
                .noneMatch(n -> n < 0);
        System.out.println("No negative prices found: " + noNegatives);
        // Output: No negative prices found: true
        List<Integer> mixed = Arrays.asList(100, -10, 50);
        boolean noNegativesMixed = mixed.stream()
                .noneMatch(n -> n < 0);
        System.out.println("No negatives in mixed list: " + noNegativesMixed);
        // Output: No negatives in mixed list: false
        
        //reduce() — reduces stream to a single value (very important in interviews!)
        // with identity
        // 1. Standard Summation using reduce
        int sum = IntStream.of(1, 2, 3, 4, 5)
                .reduce(0, Integer::sum);
        System.out.println("Sum: " + sum); // Output: 15
        // 2. Multiplication using reduce (Identity must be 1)
        int product = IntStream.of(1, 2, 3, 4, 5)
                .reduce(1, (a, b) -> a * b);
        System.out.println("Product: " + product); // Output: 120
        // without identity - returns Optional
        List<Integer> numbers2 = Arrays.asList(2, 3, 4);
        // Reduce without an identity value
        Optional<Integer> product1 = numbers2.stream()
                .reduce((a, b) -> a * b);
        // Handle the result
        product1.ifPresent(p -> System.out.println("Product: " + p));
        // Output: Product: 24
        // Example with an empty list
        List<Integer> emptyList = Arrays.asList();
        Optional<Integer> emptyProduct = emptyList.stream()
                .reduce((a, b) -> a * b);
        System.out.println("Is present? " + emptyProduct.isPresent());
        // Output: Is present? false
        // with identity + combiner (for parallel)
        List<Integer> numbers3 = Arrays.asList(1, 2, 3, 4, 5, 6);
        // Using the three-argument reduce
        int result2 = numbers3.parallelStream()
                .reduce(0,
                        Integer::sum, // Accumulator
                        (sum1, sum2) -> sum1 + sum2);    // Combiner
        System.out.println("Total Sum: " + result2);
        // Output: Total Sum: 21
        
        //min() / max() — find minimum/maximum element
        List<Integer> numbers4 = Arrays.asList(42, 12, 89, 7, 55);
        // Finds the smallest number (7)
        Optional<Integer> min = numbers4.stream()
                .min(Comparator.naturalOrder());
        min.ifPresent(n -> System.out.println("Minimum: " + n));
        // Output: Minimum: 7
        List<String> words = Arrays.asList("apple", "banana", "kiwi", "strawberry");
        // Finds the string with the greatest length
        Optional<String> max = words.stream()
                .max(Comparator.comparing(String::length));
        max.ifPresent(s -> System.out.println("Longest word: " + s));
        // Output: Longest word: strawberry
        
        //toArray() — collects into an array
//        Object[] arr = stream.toArray();
//        String[] arr = stream.toArray(String[]::new);
        
        //toList() ( 16+) — shorthand for collect(Collectors.toList())
//        List<String> list = stream.filter(...).toList(); // unmodifiable
        
        //sum() / average() / summaryStatistics() — only on primitive streams
        int sum1 = IntStream.of(1,2,3).sum();
        OptionalDouble avg = IntStream.of(1,2,3).average();
        IntSummaryStatistics stats = IntStream.of(1,2,3,4,5).summaryStatistics();
        // stats.getMin(), getMax(), getSum(), getCount(), getAverage()

        System.out.println("Sum: "+sum1);
        System.out.println("Avg: "+avg);
        System.out.println("Stats: "+stats);
    }
}
