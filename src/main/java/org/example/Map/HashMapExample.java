package org.example.Map;

import java.util.HashMap;

public class HashMapExample {
    int id;
    String name;
    String email;

    @Override
    public int hashCode() {
        return (int) id * name.hashCode() * email.hashCode();
    }
    public static void main(String[] args) {
        HashMap<String, Integer> ages = new HashMap<>();
        ages.put("Alice", 30);
        ages.put("Bob", 25);
        System.out.println("HashMap after put: " + ages); // Output: {Bob=25, Alice=30}
    }
}
