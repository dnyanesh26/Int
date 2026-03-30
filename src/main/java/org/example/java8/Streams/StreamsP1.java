package org.example.java8.Streams;

import org.example.java8.Streams.pojo.Person;
import org.example.java8.Streams.pojo.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsP1 {

    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> strings = Arrays
                .asList("apple", "banana", "cherry", "date", "grapefruit");
        Optional<String> longestString = strings.stream().max(Comparator.comparing(String::length));
        longestString.ifPresent(System.out::println);

        List<Person> persons = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35)
        );

        OptionalDouble avgAge = persons.stream().mapToInt(Person::getAge).average();
        avgAge.ifPresent(System.out::println);

        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10, 11, 12, 13, 14, 15);
        boolean primePresent = numbers.stream().anyMatch(n -> new StreamsP1().isPrime(n));
        System.out.print(primePresent);

        //TODO:
        List<Integer> list1 = Arrays.asList(1, 3, 5, 7, 9);
        List<Integer> list2 = Arrays.asList(2, 4, 6, 8, 10);
        List<Integer> mergedList = Stream.concat(list1.stream(), list2.stream())
                .sorted()
                .collect(Collectors.toList());

        //TODO:
        List<Integer> list3 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list4 = Arrays.asList(3, 4, 5, 6, 7);
        List<Integer> intersection = list3.stream()
                .filter(list4::contains)
                .collect(Collectors.toList());

        List<Integer> numbersWithDuplicates = Arrays.asList(1, 2, 3, 2, 4, 1, 5, 6, 5);
        List<Integer> uniqueNumbers = numbersWithDuplicates
                .stream()
                .distinct()
                .collect(Collectors.toList());

        List<Transaction> transactions = Arrays.asList(
                new Transaction("2022-01-01", 100),
                new Transaction("2022-01-01", 200),
                new Transaction("2022-01-02", 300),
                new Transaction("2022-01-02", 400),
                new Transaction("2022-01-03", 500)
        );
        //TODO:
        Map<String,Integer> trans = transactions.stream().collect(Collectors.groupingBy(Transaction::getDate,
                Collectors.summingInt(Transaction::getAmount)));
        System.out.println("Sum of amount by days: "+ trans);

        int[] array = {4, 2, 7, 1, 5, 3, 6};
        int k = 3; // Find the 3rd smallest element
//        int kthSmallest = Arrays.stream(array)
//                .sorted()
//                .skip(k - 1)
//                .findFirst()
//                .orElse(-1);
        int kthsmallest = Arrays.stream(array).sorted().skip(k-1).limit(1).sum();
        System.out.println("kthSmallest: "+kthsmallest);

        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry",
                "banana", "apple");
        Map<String,Long> freq = words.stream().collect(Collectors.groupingBy(Object::toString,Collectors.counting()));
        System.out.println("Freq: "+freq);

        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Map<Boolean, List<Integer>> partition = numbers1.stream().collect(Collectors.partitioningBy(n->n%2 == 0));
        System.out.println("partitioned: "+partition);

    }
}
