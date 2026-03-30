package org.example.Collections.Map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;

public class ConcurrentHashMapMastery {
    public static void main(String[] args) {
        // 1. Initialization
        // mappingCount() is preferred for ConcurrentHashMap over size()
        ConcurrentHashMap<String, Integer> inventory = new ConcurrentHashMap<>();

        // 2. Basic CRUD & Views
        inventory.put("Laptop", 50);
        inventory.put("Mouse", 150);
        System.out.println("Initial size: " + inventory.mappingCount());
        System.out.println("Get Laptop: " + inventory.getOrDefault("Laptop", 0));

        // 3. Atomic Conditional Updates
        // putIfAbsent: Only adds if key is missing
        inventory.putIfAbsent("Keyboard", 100);

        // replace: Only replaces if the current value matches (Safe update)
        boolean isReplaced = inventory.replace("Laptop", 50, 45);

        // remove: Only removes if key AND value match
        inventory.remove("Mouse", 100); // Won't remove, value is 150
        inventory.remove("Keyboard");

        // 4. Computing Functions (Functional style)
        // computeIfAbsent: Useful for initializing collections
        ConcurrentHashMap<String, List<String>> logs = new ConcurrentHashMap<>();
        logs.computeIfAbsent("ErrorLog", k -> new ArrayList<>()).add("System Overload");

        // computeIfPresent: Update only if exists
        inventory.computeIfPresent("Keyboard", (key, val) -> val - 10);

        // merge: The most powerful update tool (Sums existing 45 + new 5)
        inventory.merge("Laptop", 5, Integer::sum);

        // 5. Bulk Parallel Operations (Java 8+)
        // Threshold 1 means "Parallelize if map has >= 1 element"
        long threshold = 1;

        // forEach: Parallel iteration
        inventory.forEach(threshold, (k, v) -> System.out.println("Item: " + k + " | Qty: " + v));

        // search: Find the first key where quantity > 40
        String popularItem = inventory.search(threshold, (k, v) -> v > 40 ? k : null);

        // reduce: Sum all quantities in the map in parallel
        int totalStock = inventory.reduceValues(threshold, Integer::sum);

        // 6. Legacy / Collection Views
        Enumeration<String> keys = inventory.keys(); // Legacy Enumeration
        inventory.keySet().remove("Keyboard"); // Removing from view removes from map

        // 7. Cleanup
        System.out.println("Final Inventory: " + inventory);
        System.out.println("Popular Item found: " + popularItem);
        System.out.println("Total stock across all items: " + totalStock);
        inventory.clear();
    }
}

