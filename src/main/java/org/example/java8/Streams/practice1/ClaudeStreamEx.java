package org.example.java8.Streams.practice1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ClaudeStreamEx {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        Map<Boolean, List<Integer>> partitioned = numbers.stream().collect(
                Collectors.partitioningBy(n -> n%2==0));
        System.out.println(partitioned);

        String input = "Java Concept Of The Day";
        Map<Character, Long> charFrequency = input.chars().mapToObj(c ->(char)c).collect(Collectors.groupingBy(Function.identity(),
                Collectors.counting()));
        System.out.println(charFrequency);

        List<String> languages = Arrays.asList(     "Java", "Python", "Java", "Kotlin", "Python" );
        List<String> uniqueLanguages = languages.stream().distinct().toList();
        System.out.println(uniqueLanguages);

        int[] numbers1 = {5, 9, 11, 2, 8, 21, 1};
        Integer secondHighest = Arrays.stream(numbers1).boxed().sorted(Comparator.reverseOrder()).distinct().skip(1).limit(1).findFirst().orElse(0);
        System.out.println(secondHighest);

        List<String> words = Arrays.asList(     "Java", "Spring", "Boot", "Microservices", "API" );
        List<String> sortedByLength = words.stream().sorted(Comparator.comparing(String::length)).toList();
        System.out.println(sortedByLength);

        List<String> items = Arrays.asList("Java", "Spring", "Boot");
        String result = items.stream().collect(Collectors.joining(", ","[","["));
        System.out.println(result);
    }
}
