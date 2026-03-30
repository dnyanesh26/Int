package org.example.java8.Lambda;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

interface Test{
    public int sum(int a, int b);
}
public class LambdaExp {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello World");
        r.run();

        Test sum = (a,b) -> a+b;
        System.out.println(sum.sum(5,10));
        Consumer<String> c = s -> System.out.println(s);
        c.accept("Consumer");

        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println(add.apply(10, 21));

        Comparator<String> comp = (String first, String second) -> first.compareTo(second);
        System.out.println(comp.compare("A", "A"));

        Function<Integer, Integer> square = x -> x * x;
        System.out.println(square.apply(12));

        Function<Integer, String> parity = n -> {
            if (n % 2 == 0)
                return "Even";
            else
                return "Odd";
        };
        System.out.println(parity.apply(123));
    }
}
