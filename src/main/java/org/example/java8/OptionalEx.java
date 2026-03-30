package org.example.java8;

import org.example.designPattern.Builder.User;

import java.util.Optional;

public class OptionalEx {

    public static void main(String[] args) {
        // Creating Optional
        Optional<String> optional = Optional.of("Hello");
        Optional<String> empty = Optional.empty();
        Optional<String> nullable = Optional.ofNullable(null);

        System.out.println(optional.orElse("empty"));
        // Using Optional
        String name = null;
        Optional<String> optName = Optional.ofNullable(name);
        System.out.println(optName);
        System.out.println(optName.orElse("empty"));

        // Get value or default
        String result = optName.orElse("Default Name");

        // Execute code if present
        optName.ifPresent(n -> System.out.println("Name: " + n));

        // Chain operations
        String upperName = optName
                .map(String::toUpperCase)
                .orElse("NO NAME");
        // Usage
//        findUserById("123")
//                .map(User::getEmail)
//                .ifPresent(email -> sendEmail(email));
    }

    // Real-world example
//    public static Optional<User> findUserById(String id) {
//        User user = database.getUser(id); // might return null
//        return Optional.ofNullable(user);
//    }
}
