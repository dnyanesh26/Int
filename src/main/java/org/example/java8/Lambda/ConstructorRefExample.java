package org.example.java8.Lambda;

import java.util.function.Supplier;

public class ConstructorRefExample {

    public static void main(String[] args) {
        // Using a lambda expression to create a new StringBuilder
        Supplier<StringBuilder> lambdaSupplier = () -> new StringBuilder();
        StringBuilder sb1 = lambdaSupplier.get();
        sb1.append("Hello from lambda!");
        System.out.println(sb1.toString()); // Output: Hello from lambda!

        // Using a constructor method reference to achieve the same result
        Supplier<StringBuilder> constructorRefSupplier = StringBuilder::new;
        StringBuilder sb2 = constructorRefSupplier.get();
        sb2.append("Hello from constructor reference!");
        System.out.println(sb2.toString()); // Output: Hello from constructor reference!
    }
}

