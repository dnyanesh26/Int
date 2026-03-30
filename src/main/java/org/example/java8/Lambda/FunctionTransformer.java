package org.example.java8.Lambda;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class FunctionTransformer {

    public static void main(String[] args) {

        Function<String, Integer> strLength = s -> s.length();
        BiFunction<Integer, Integer, String> sumToString = (a, b) -> "Sum: " + (a + b);
        BinaryOperator<Integer> multiply = (a, b) -> a * b;

        System.out.println(strLength.apply("River"));
        System.out.println(sumToString.apply(13, 47));
        System.out.println(multiply.apply(13, 11));
    }

}
