package org.example.java8.Lambda;

import java.util.function.*;

public class ConsumerWorker {

    public static void main(String[] args) {
        Consumer<String> printer = s -> System.out.println(s);

        BiConsumer<String, Integer> scoreReporter = (name, score) -> System.out.println(name + " scored " + score);

        ObjIntConsumer<String> repeatPrint = (s, n) -> {
            for (int i = 0; i < n; i++)
                System.out.println(s);
        };

        printer.accept("Watashi");
        scoreReporter.accept("Jason", 69);
        repeatPrint.accept("Shiine", 3);
    }
}
