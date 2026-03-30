package org.example.Collections.Map;

import java.util.concurrent.ConcurrentHashMap;

public class ComputeDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // 1. Using compute to initialize AND increment
        // Even if "ClickCount" doesn't exist, compute handles the null check.
        map.compute("ClickCount", (key, value) -> (value == null) ? 1 : value + 1);

        // 2. Using compute to update existing value
        map.compute("ClickCount", (key, value) -> value + 10);

        // 3. Using compute to REMOVE an entry
        // Returning null removes the key from the map.
        map.compute("ClickCount", (key, value) -> null);

        System.out.println("Map contains ClickCount: " + map.containsKey("ClickCount")); // false
    }
}
