package org.example.java8.Streams;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTerminalCollectors {

    public static void main(String[] args) {

        AtomicInteger a = new AtomicInteger(1);
        List<Integer> list = Stream.generate(a::getAndIncrement).limit(20).filter(x -> x % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Collectors toList");
        for(Integer i: list){
            System.out.print(i+" ");
        }
        a.set(1);
        System.out.println();

        Set<Integer> set = Stream.generate(a::getAndIncrement).limit(20).filter(x -> x % 2 == 0)
                .collect(Collectors.toSet());
        System.out.println("Collectors toSet "+set);
        a.set(1);

        Map<Integer, List<String>> map = Arrays.stream(new String[]{"hello", "domo", "Arigato", "Arigato"})
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Collectors groupingBy: "+map);

        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange");
        String joined = fruits.stream()
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Collectors joining: "+joined);


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        Map<Boolean, List<Integer>> partitioned = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Collectors partitioningBy: "+ partitioned);
        System.out.println("Even numbers: " + partitioned.get(true));  // [2, 4, 6]
        System.out.println("Odd numbers: " + partitioned.get(false));  // [1, 3, 5]
        List<String> words = Arrays.asList(
                "apple", "banana", "fruit", "cherry",
                "banana", "apple", "date", "elderberry",
                "cherry", "fig", "apple"
        );


        System.out.println("Collectors counting: ");
        Map<String, Long> counts = words.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(counts);

    }
}
