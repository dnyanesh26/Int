package org.example.Collections.Claude;

import java.util.*;
import java.util.stream.Collectors;

public class ImmutableCollections {
    public static void main(String[] args) {

        // Unmodifiable WRAPPER - underlying list can still change
        List<String> mutable = new ArrayList<>(Arrays.asList("a", "b", "c"));
        List<String> unmodifiable = Collections.unmodifiableList(mutable);
        mutable.add("d"); // modifies through original reference
        System.out.println(unmodifiable); // [a, b, c, d] - changed!

        // Java 9+ List.of() - truly immutable, no nulls
        List<String> immutable = List.of("x", "y", "z");
        try { immutable.add("w"); }
        catch (UnsupportedOperationException e) { System.out.println("Cannot modify!"); }

        // Map.of() and Map.entry()
        Map<String, Integer> immutableMap = Map.of(
                "one", 1, "two", 2, "three", 3);

        // Map.ofEntries() for >10 entries
        Map<String, Integer> bigMap = Map.ofEntries(
                Map.entry("a", 1), Map.entry("b", 2), Map.entry("c", 3)
        );

        // Copyof - defensive immutable copy
        List<String> copy = List.copyOf(mutable); // snapshot, immutable
        mutable.add("e"); // copy unaffected
        System.out.println(copy.size()); // 4, not 5

        // Stream -> unmodifiable
        List<Integer> immutableFromStream = List.of(1,2,3,4,5).stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toUnmodifiableList());
        System.out.println(immutableFromStream);

        // For mutation-heavy scenarios: copy, mutate, freeze
        List<String> working = new ArrayList<>(immutable);
        working.add("new");
        List<String> frozen = List.copyOf(working); // immutable again
    }
}

