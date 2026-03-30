package org.example.java8.Streams;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsP2 {
    public static void main(String[] args) {
        Stream<Long> fibonacci = Stream.iterate(
                        new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
                .mapToLong(f -> f[0]).boxed();
        // Print first 10 Fibonacci numbers
        fibonacci.limit(10).forEach(n -> System.out.print(n + " "));

        System.out.println();

        IntStream fibStream = IntStream.generate(new IntSupplier() {
            private int prev = 0;
            private int curr = 1;

            @Override
            public int getAsInt() {
                int next = prev;
                int sum = prev + curr;
                prev = curr;
                curr = sum;
                return next;
            }
        }).limit(10);

//TODO: Bookmarks

//        List<Integer> numbers = Arrays.asList(5, 2, 1, 3, 4);
//        OptionalDouble median = numbers.stream()
//                .sorted()
//                .mapToInt(Integer::intValue)
//                .collect(IntStatistics.summaryStatistics())
//                .getMedian();

        List<String> strings = Arrays.asList("flower", "flow", "flight");
        Optional<String> longestPrefix = strings.stream().reduce(
                (s1,s2) -> {
                    int minLen = Math.min(s1.length(),s2.length());
                    int i = 0;
                    while (i < minLen && s1.charAt(i) == s2.charAt(i) ){
                        i++;
                    }
                    return s1.substring(0,i);
                }
        );
        System.out.println(longestPrefix.get());

        int[] array = {1, 4, 3, 6, 2, 7, 8};
        int maxProd = IntStream.range(0,array.length).mapToObj(
                            i -> IntStream.range(i+1,array.length)
                                    .map(j -> array[i]*array[j])
                                    .max()
                                    .orElse(Integer.MIN_VALUE))
                .max(Integer::compare).orElse(Integer.MIN_VALUE);
        System.out.println("maxProd: "+maxProd);

        List<String> words = Arrays.asList("listen", "silent", "hello",
                "world", "night", "thing");
        Map<String,List<String>> anagram = words.stream().collect(Collectors.groupingBy(
                                                word -> {
                                                    char [] chararr = word.toCharArray();
                                                    Arrays.sort(chararr);
                                                    return Arrays.toString(chararr);
                                                })
        );

        System.out.println("anagram: "+anagram);

        //TODO:
        List<String> strings1 = Arrays.asList("apple", "banana", "orange",
                "grape", "melon");
        char target = 'a';
        long occChar = strings1.stream().flatMapToInt(String::chars).filter(c -> c==target).count();

        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10);
        int target1 = 12;
        Set<String> sumInt = numbers.stream().flatMap(
                    i -> numbers.stream().map(j -> j+i==target1?"("+i+","+j+")":""))
                .collect(Collectors.toSet());

        System.out.println("2sum Set: "+ sumInt);

        List<Map.Entry<Integer,Integer>> twoSum = numbers.stream()
                .flatMap(i -> numbers.stream()
                        // Filter for pairs that sum to the target
                        .filter(j -> i + j == target1)
                        // Filter to ensure unique pairs (e.g., (4, 12) not also (12, 4))
                        // This condition assumes distinct numbers in the input list for simplicity.
                        // For a list with duplicates, more complex logic (like using indices or sorting) is needed
                        .filter(j -> i < j)
                        // Map the pair to a Map.Entry for convenient storage
                        .map(j -> new AbstractMap.SimpleEntry<>(i, j))
                )
                .collect(Collectors.toList());

        System.out.println("2Sum MapEntry: "+twoSum);

        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 2, 4, 5, 6, 4, 7, 8, 9, 9);

        Map<Integer,Long> freq = numbers2.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        List<Integer> nondup = numbers2.stream().filter(n -> freq.get(n)==1).toList();
        System.out.println(nondup);

        List<String> strings2 = Arrays.asList("apple", "banana", "orange",
                "grape", "kiwi");
        Optional<String> longestString = strings2.stream().max(Comparator.comparing(String::length));
        longestString.ifPresent(System.out::println);
    }
}
