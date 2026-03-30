package org.example.java8.Lambda;

import java.util.function.*;

public class PredicateFilter {

    public interface Predicate<T> {
        public boolean test(T s);
    }

    public static void main(String[] args) {

        Predicate<String> isEmpty = s -> s.isEmpty();

        BiPredicate<String, Integer> isLongerThan = (s, n) -> s.length() > n;

        IntPredicate isEven = (n) -> n % 2 == 0;

        System.out.println(isEmpty.test("Hello"));
        System.out.println(isLongerThan.test("Horcroucx", 7));
        System.out.println(isEven.test(13));
    }
}
