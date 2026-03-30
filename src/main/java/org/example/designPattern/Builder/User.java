package org.example.designPattern.Builder;

public class User {
    private final String name;     // Mandatory
    private final String email;    // Mandatory
    private final int age;         // Optional

    private User(UserBuilder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
    }

    // 1. Define interfaces for each mandatory step
    public interface NameStep {
        EmailStep name(String name);
    }

    public interface EmailStep {
        FinalStep email(String email);
    }

    // 2. The final step contains optional methods and the build() method
    public interface FinalStep {
        FinalStep age(int age);
        User build();
    }

    // 3. The Builder implements all interfaces in the chain
    public static class UserBuilder implements NameStep, EmailStep, FinalStep {
        private String name;
        private String email;
        private int age;

        @Override
        public EmailStep name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public FinalStep email(String email) {
            this.email = email;
            return this;
        }

        @Override
        public FinalStep age(int age) {
            this.age = age;
            return this;
        }

        @Override
        public User build() {
            return new User(this);
        }
    }

    // Entry point: Forces the user into the first step
    public static NameStep builder() {
        return new UserBuilder();
    }

    public static void main(String[] args) {
        // Correct usage: Complies and works
        User user = User.builder()
                .name("Alice")
                .email("alice@example.com")
                .age(30)
                .build();
/*
// Error: Will not compile because .email() is mandatory after .name()
        User incomplete = User.builder()
                .name("Bob")
                .build(); // Compiler error: .build() not found on EmailStep
*/
    }
}

