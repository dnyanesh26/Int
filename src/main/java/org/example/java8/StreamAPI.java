package org.example.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamAPI {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Filter even numbers, square them, and collect
        List<Integer> result = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .collect(Collectors.toList());
        // Result: [4, 16, 36, 64, 100]

        // Sum of all numbers
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        // Result: 55

        // Find first element greater than 5
        Optional<Integer> first = numbers.stream()
                .filter(n -> n > 5)
                .findFirst();
    }
}
