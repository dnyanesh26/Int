package org.example.Collections.Claude;

import java.util.*;
import java.util.stream.*;

public class CollectorsDemo {
    record Employee(String name, String dept, double salary) {}

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Alice",  "Eng",   120_000),
                new Employee("Bob",    "Eng",    95_000),
                new Employee("Carol",  "HR",     80_000),
                new Employee("Dave",   "HR",     85_000),
                new Employee("Eve",    "Fin",   110_000)
        );

        // Group by department
        Map<String, List<Employee>> byDept =
                employees.stream().collect(Collectors.groupingBy(Employee::dept));

        // Average salary per department
        Map<String, Double> avgSalary = employees.stream().collect(
                Collectors.groupingBy(Employee::dept,
                        Collectors.averagingDouble(Employee::salary)));
        System.out.println(avgSalary); // {Eng=107500.0, HR=82500.0, Fin=110000.0}

        // Count per department
        Map<String, Long> countByDept = employees.stream().collect(
                Collectors.groupingBy(Employee::dept, Collectors.counting()));

        // Partition: salary >= 100k
        Map<Boolean, List<Employee>> partitioned = employees.stream().collect(
                Collectors.partitioningBy(e -> e.salary() >= 100_000));
        System.out.println("High earners: " + partitioned.get(true).size());

        // Multi-level grouping: dept -> salary bracket
        Map<String, Map<String, Long>> multilevel = employees.stream().collect(
                Collectors.groupingBy(Employee::dept,
                        Collectors.groupingBy(
                                e -> e.salary() >= 100_000 ? "senior" : "junior",
                                Collectors.counting())));

        // toMap with merge function (handle duplicate keys)
        Map<String, Double> nameSalary = employees.stream().collect(
                Collectors.toMap(
                        Employee::name,
                        Employee::salary,
                        Double::max,  // merge: keep higher salary on collision
                        LinkedHashMap::new  // use LinkedHashMap to preserve order
                ));

        // Statistics
        DoubleSummaryStatistics stats = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::salary));
        System.out.println("Max: " + stats.getMax() + " Min: " + stats.getMin());
    }
}

