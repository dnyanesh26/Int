package org.example.java8.Streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileProcessor {

    // A simple class to hold our parsed data (e.g., a record with name and age)
    public static class Record {
        private final String name;
        private final int age;

        public Record(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Record{name='" + name + "', age=" + age + '}';
        }
    }

    /**
     * Validates and parses a single line into a Record object.
     * Throws an exception if the line is invalid.
     */
    public static Record parseAndValidateLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }
        String name = parts[0].trim();
        int age;
        try {
            age = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid age format: " + parts[1].trim());
        }
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Age out of range: " + age);
        }
        return new Record(name, age);
    }

    public static void main(String[] args) {
        Path filePath = Paths.get("data.txt"); // Replace with your file path

        try (Stream<String> linesStream = Files.lines(filePath)) {
            List<Record> validRecords = linesStream
                    .filter(line -> !line.trim().isEmpty()) // Skip empty lines
                    .map(line -> {
                        try {
                            return parseAndValidateLine(line); // Attempt to parse and validate
                        } catch (IllegalArgumentException e) {
                            System.err.println("Skipping invalid line: " + e.getMessage());
                            return null; // Return null for invalid lines
                        }
                    })
                    .filter(record -> record != null) // Filter out nulls (invalid lines)
                    .collect(Collectors.toList()); // Collect valid records into a List

            System.out.println("Valid records processed:");
            validRecords.forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
