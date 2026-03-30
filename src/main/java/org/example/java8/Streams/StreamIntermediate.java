package org.example.java8.Streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamIntermediate {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("filter() — filters elements based on a predicate");
        AtomicInteger a = new AtomicInteger(1);
        Stream<Integer> stream1 = Stream.generate(a::getAndIncrement).limit(20);
        stream1.filter(n -> (n < 10 || n > 17)).forEach(s -> System.out.print(s+" "));
        System.out.println("");

        System.out.println("map() — transforms each element");
        String str = "domo arigato";
        Stream<String> stream2 = Stream.generate(()-> str).peek(s -> System.out.print(s+" ")).limit(3).map(String::toUpperCase);
        stream2.map(String::length).forEach(s -> System.out.print(s+" "));
        System.out.println("");


        System.out.println("flatMap() — flattens nested structures (most asked in interviews!)");
        List<List<Integer>> nested = List.of(List.of(1,2), List.of(3,4));
        List <Integer> nums =nested.stream()
                .flatMap(Collection::stream)
                .toList(); // [1, 2, 3, 4]
        for(Integer n: nums){
            System.out.print(n+" ");
        }
        System.out.println("");

        System.out.println("distinct() — removes duplicates (uses equals/hashCode)");
        Stream<String> stream3 = Arrays.stream(new String[]{"hello", "domo", "Arigato", "Arigato"});
        stream3.distinct().forEach(s -> System.out.print(s+" "));

        System.out.println("");
        System.out.println("sorted() — natural or custom order");
        Stream<Double> stream4 = Stream.generate(Math::random).limit(5).peek(s -> System.out.print(s+" "));
        Thread.sleep(1000);
        System.out.println("post peek 1");
        stream4.sorted().peek(s -> System.out.print(s+" ")).sorted(Comparator.reverseOrder()).forEach(s -> System.out.print(s+" "));
        System.out.println("post peek 2");
        //stream4.sorted(Comparator.reverseOrder()).forEach(System.out::print); this will give stream already operated upon or closed error
        //stream.sorted(Comparator.comparing(Person::getAge))

        System.out.println("");
        System.out.println("peek() — for debugging, performs action without consuming");
        //stream.peek(System.out::println)

        System.out.println("");
        System.out.println("limit() — truncates stream to given size");
        //stream.limit(5)

        System.out.println("");
        System.out.println("skip() — skips first N elements");
        Stream<String> stream5 = Arrays.stream(new String[]{"hello", "hello", "domo", "Arigato"});
        stream5.skip(1).forEach(s -> System.out.print(s+" "));
        System.out.println("");

        System.out.println("mapToInt() / mapToLong() / mapToDouble() — converts to primitive streams");
        int sum = Arrays.stream(new String[]{"hello", "hello"})
        .mapToInt(String::length)
                .sum();
        System.out.print(sum);
        System.out.println("");

        System.out.println("mapToObj() — converts primitive stream back to object stream");

        Stream<Object> intStream = IntStream.range(1, 5).mapToObj(i -> "Item " + i);
        intStream.map(Object::toString).forEach(s -> System.out.print(s+" "));
        System.out.println("");

        System.out.println("flatMapToInt/Long/Double() — flatMap for primitive streams");
        List<List<Integer>> nested1 = List.of(List.of(1), List.of(2,4,52));
        nested1.stream().flatMapToInt(s -> IntStream.of(s.stream().mapToInt(i->i).toArray())).forEach(s -> System.out.print(s+" "));
        System.out.println("");

        System.out.println("takeWhile() ( 9+) — takes elements while predicate is true, then stops");
        Stream.of(1,2,3,4,5).takeWhile(n -> n < 4).forEach(s -> System.out.print(s+" "));
        System.out.println("");


        System.out.println("dropWhile() ( 9+) — drops elements while predicate is true, then takes rest");
        Stream.of(1,2,3,4,5,2,6,1,7).dropWhile(n -> n < 4).forEach(s -> System.out.print(s+" "));
        System.out.println("");
    }
}
