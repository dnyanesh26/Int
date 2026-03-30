package org.example.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaEx {

    public static void main(String[] args) {
        // Old way (before Java 8)
        List<String> names = Arrays.asList("John", "Jane", "Bob");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });

        // Java 8 way with Lambda
        Collections.sort(names, (a, b) -> a.compareTo(b));
        // Or even simpler
        names.sort(String::compareTo);
    }
}
