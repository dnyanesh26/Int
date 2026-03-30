package org.example.java8.Streams;

import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



public class df {



    public static void main(String[] args) {
        List<String> listOfStrings = Arrays.asList("Java", "Python", "C#", "HTML", "Kotlin", "C++", "COBOL", "C");



        // sort it based on length of the string

        List <String> result = listOfStrings.stream()
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList()).reversed();

        System.out.println(result);
    }
}
