package org.example.java8;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctInterface {
    // Built-in functional interfaces
    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }
    public static void main(String[] args) {


// Usage
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;

        System.out.println(add.calculate(5, 3));      // 8
        System.out.println(multiply.calculate(5, 3)); // 15

// Common built-in functional interfaces:

// Predicate<T> - takes T, returns boolean
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println(isEven.test(4)); // true

// Function<T, R> - takes T, returns R
        Function<String, Integer> length = s -> s.length();
        System.out.println(length.apply("Hello")); // 5

// Consumer<T> - takes T, returns void
        Consumer<String> printer = s -> System.out.println(s);
        printer.accept("Hello"); // Hello

// Supplier<T> - takes nothing, returns T
        Supplier<Double> random = () -> Math.random();
        System.out.println(random.get()); // random number
    }
}
