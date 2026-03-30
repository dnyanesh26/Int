package org.example.java8.Streams.practice1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String department;
    private int yearOfJoining;
    private double salary;

    public Employee(int id, String name, int age, String gender, String department, int yearOfJoining, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.yearOfJoining = yearOfJoining;
        this.salary = salary;
    }

    // Getters
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public String getGender() { return gender; }
    public String getName() { return name; }
    public int getAge() { return age; }
}

public class StreamInterview {
    public static List<Employee> getEmployees() {
        return Arrays.asList(
                new Employee(1, "Jhansi", 32, "Female", "Sales", 2011, 25000.0),
                new Employee(2, "Smith", 25, "Male", "Sales", 2015, 13500.0),
                new Employee(3, "David", 29, "Male", "Infrastructure", 2012, 18000.0),
                new Employee(4, "Orlen", 28, "Male", "Development", 2014, 32500.0),
                new Employee(5, "Charles", 27, "Male", "Sales", 2013, 22700.0),
                new Employee(6, "Cathy", 43, "Female", "Infrastructure", 2016, 10500.0),
                new Employee(7, "Ramesh", 35, "Male", "Infrastructure", 2010, 27000.0),
                new Employee(8, "Suresh", 31, "Male", "Development", 2015, 34500.0)
        );
    }

    public static void main(String[] args) {

        Map<String,String> highestInDept = getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
                                opt -> opt.map(Employee::getName).orElse("")
                                )
                        ));
        System.out.println(highestInDept);

        DoubleSummaryStatistics stats = getEmployees().stream().collect(
                        Collectors.summarizingDouble(Employee::getSalary));
        double sum = stats.getSum();
        double avg = stats.getAverage();

        Map<Boolean, List<String>> partition = getEmployees().stream().collect(Collectors.partitioningBy(n -> n.getAge()>30,Collectors.mapping(Employee::getName,Collectors.toList())));

        System.out.println(partition);

        double secondHighest = getEmployees().stream().map(e->e.getSalary()).distinct().sorted(Comparator.reverseOrder()).skip(1).limit(1).findFirst().get();
        System.out.println(secondHighest);

        String names = getEmployees().stream().map(Employee::getName).collect(Collectors.joining(", ","[","]"));
        System.out.println(names);

        String input = "Java Concept Of The Day";
        Map<Character, Long> charFrequency = input.chars().mapToObj(c->(char) c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        List<String> words = Arrays.asList(     "Java", "Spring", "Boot", "Microservices", "API" );
        List<String> sortedByLength = words.stream().sorted(Comparator.comparingInt(String::length).reversed()).toList();
        System.out.println(sortedByLength);

        int[] numbers = {5, 9, 11, 2, 8, 21, 1};
        Integer secondHighestNum = Arrays.stream(numbers).boxed().sorted(Comparator.reverseOrder()).skip(1).limit(1).findFirst().orElse(0);
        System.out.println(secondHighestNum);
    }

}