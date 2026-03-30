package org.example.java8.Streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamCreation {

    public static void main(String[] args) throws IOException {

        //From a Collection
        List<String> list = List.of("a", "b", "c");
        Stream<String> stream = list.stream();
        Stream<String> parallelStream = list.parallelStream();

        //From an Array
        Stream<String> stream1 = Arrays.stream(new String[]{"a", "b", "c"});
        IntStream intStream = Arrays.stream(new int[]{1, 2, 3});

        //Using Stream.of()
        Stream<String> stream2 = Stream.of("a", "b", "c");

        //Using Stream.builder()
        Stream<String> stream3 = Stream.<String>builder()
                .add("a").add("b").add("c")
                .build();

        //Using Stream.iterate()
        // Infinite stream - seed, unary operator
        Stream<Integer> stream4 = Stream.iterate(0, n -> n + 2).limit(5);
        // 0, 2, 4, 6, 8

        // Java 9+ with predicate (finite)
        Stream<Integer> stream5 = Stream.iterate(0, n -> n < 10, n -> n + 2);

        //Using Stream.generate()
        // Infinite stream from a Supplier
        Stream<Double> stream6 = Stream.generate(Math::random).limit(5);
        Stream<String> stream7 = Stream.generate(() -> "hello").limit(3);

        //From a String / CharSequence
        IntStream stream8 = "hello".chars();  // stream of char values
        Stream<String> stream9 = Pattern.compile(",").splitAsStream("a,b,c");

        //Using Files / I/O (NIO)
        Stream<String> lines = Files.lines(Paths.get("file.txt"));
        Stream<Path> paths = Files.walk(Paths.get("/some/dir"));
        Stream<Path> paths1 = Files.list(Paths.get("/some/dir"));

        //Using IntStream, LongStream, DoubleStream (Primitive Streams)
        IntStream range = IntStream.range(1, 10);        // 1 to 9
        IntStream rangeClosed = IntStream.rangeClosed(1, 10); // 1 to 10
        LongStream longStream = LongStream.range(1L, 100L);
        DoubleStream doubles = DoubleStream.of(1.1, 2.2, 3.3);

        //Stream.empty() and Stream.concat()
        Stream<String> empty = Stream.empty();
        Stream<String> combined = Stream.concat(stream1, stream2);

        //From Optional (Java 9+)
        Optional<String> opt = Optional.of("hello");
        Stream<String> stream10 = opt.stream(); // stream of 0 or 1 element

        //From a Map
        Map<String, Integer> map = Map.of("a", 1, "b", 2);
        Stream<Map.Entry<String, Integer>> stream11 = map.entrySet().stream();
        Stream<String> keyStream = map.keySet().stream();
        Stream<Integer> valueStream = map.values().stream();
    }
}
