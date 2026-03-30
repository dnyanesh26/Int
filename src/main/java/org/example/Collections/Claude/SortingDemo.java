package org.example.Collections.Claude;

import java.util.*;
import java.util.stream.*;
// The Employee class "knows" how to compare itself by ID
class Employee implements Comparable<Employee> {
    int id;
    String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Employee other) {
        // Natural order: sort by ID ascending
        return this.id - other.id;
    }

    @Override
    public String toString() { return id + ":" + name; }
}

        // External logic: Sorting by Name instead of ID
        class NameComparator implements Comparator<Employee> {
            @Override
            public int compare(Employee e1, Employee e2) {
                return e1.name.compareTo(e2.name);
            }
        }



        public class SortingDemo {
    record Product(String name, String category, double price, int stock) {}


    public static void main(String[] args) {
        List<Product> products = new ArrayList<>(List.of(
                new Product("Phone",   "Electronics", 699.99, 50),
                new Product("Tablet",  "Electronics", 499.99, 20),
                new Product("Shirt",   "Apparel",      29.99, 200),
                new Product("Laptop",  "Electronics", 1299.0,  5),
                new Product("Jeans",   "Apparel",      59.99, 150)
        ));

        // Multi-field sort: category ASC, price DESC
        Comparator<Product> comp = Comparator
                .comparing(Product::category)
                .thenComparing(Comparator.comparingDouble(Product::price).reversed());

        products.sort(comp);
        products.forEach(p ->
                System.out.printf("%-12s %-12s $%8.2f%n", p.category(), p.name(), p.price()));

        // Sort with null-safety
        List<String> withNulls = new ArrayList<>(Arrays.asList("b", null, "a", null, "c"));
        withNulls.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        System.out.println(withNulls); // [a, b, c, null, null]

        // PriorityQueue with custom comparator (min-heap by price)
        PriorityQueue<Product> cheapest =
                new PriorityQueue<>(Comparator.comparingDouble(Product::price));
        products.forEach(cheapest::offer);
        System.out.println("Cheapest: " + cheapest.poll().name()); // Shirt

        // Group and sort within groups
        Map<String, List<Product>> sorted = products.stream()
                .collect(Collectors.groupingBy(Product::category,
                        TreeMap::new,  // sorted keys
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> { list.sort(Comparator.comparingDouble(Product::price)); return list; }
                        )));
        System.out.println(sorted.keySet()); // [Apparel, Electronics]
        System.out.println(sorted.values());


        // Usage
        List<Employee> list = new ArrayList<>(List.of(new Employee(3, "Alice"), new Employee(1, "Bob")));
        Collections.sort(list); // Uses the internal compareTo() logic
        System.out.println(list);


        // Usage
        Collections.sort(list, new NameComparator()); // Explicitly pass the custom logic

        System.out.println(list);
        // Modern Java (Lambda) approach for Age
        list.sort(Comparator.comparing(Employee::getName,Comparator.comparingInt(String::length)));
        System.out.println(list);
    }
}

