package org.example.Streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Stream_eg {

    public static void main(String[] args) {
        List<Integer> numbersFilter = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> evenNumbers = numbersFilter.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList()); // [2, 4]

        List<String> namesMap = Arrays.asList("Alice", "Bob", "Charlie");
        List<Integer> nameLengths = namesMap.stream()
                .map(String::length)
                .collect(Collectors.toList()); // [5, 3, 7]

        List<List<String>> listOfListsflatMap = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("c", "d"));
        List<String> flattenedList = listOfListsflatMap.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList()); // ["a", "b", "c", "d"]

        List<Integer> duplicates = Arrays.asList(1, 2, 2, 3, 1, 4);
        List<Integer> unique = duplicates.stream()
                .distinct()
                .collect(Collectors.toList()); // [1, 2, 3, 4]


        List<String> unsorted = Arrays.asList("banana", "apple", "cherry");
        List<String> sorted = unsorted.stream()
                .sorted()
                .collect(Collectors.toList()); // ["apple", "banana", "cherry"]

        List<Integer> numbersLimit = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> limited = numbersLimit.stream()
                .limit(3)
                .collect(Collectors.toList()); // [1, 2, 3]

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> skipped = numbers.stream()
                .skip(2)
                .collect(Collectors.toList()); // [3, 4, 5]

        List<String> namesforEach = Arrays.asList("Alice", "Bob");
        namesforEach.stream().forEach(System.out::println); // Prints "Alice", then "Bob"

        List<String> names = Arrays.asList("Alice", "Bob");
        List<String> collectedNames = names.stream().collect(Collectors.toList()); // ["Alice", "Bob"]

        List<Integer> numbersReduce = Arrays.asList();
        Optional<Integer> sum = numbersReduce.stream().reduce(Integer::sum); // 10
        System.out.println((sum.orElse(-1)));

//        List<String> names = Arrays.asList("Alice", "Bob");
//        long count = names.stream().count(); // 2
//
//        List<Integer> numbers = Arrays.asList(5, 1, 8, 2);
//        Optional<Integer> min = numbers.stream().min(Integer::compare); // Optional[1]
//
//        List<String> names = Arrays.asList("Alice", "Bob");
//        Optional<String> first = names.stream().findFirst(); // Optional["Alice"]
//
        List<Integer> numbersanymatch = Arrays.asList(1, 3);
        boolean hasEven = numbersanymatch.stream().anyMatch(n -> n % 2 == 0); // true
        System.out.println(hasEven);
    }
}
