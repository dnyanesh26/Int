package org.example.Collections.Claude;

import java.util.*;

public class HashSetInternals {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("hello");
        set.add("hello"); // duplicate ignored — HashMap put returns existing value
        System.out.println(set.size()); // 1

        // LinkedHashSet = HashSet + insertion-order iteration
        Set<String> linked = new LinkedHashSet<>();
        linked.add("banana"); linked.add("apple"); linked.add("cherry");
        System.out.println(linked); // [banana, apple, cherry]

        // TreeSet = sorted unique elements (Red-Black Tree)
        TreeSet<Integer> sorted = new TreeSet<>();
        sorted.add(5); sorted.add(2); sorted.add(8); sorted.add(2);
        System.out.println(sorted);          // [2, 5, 8]
        System.out.println(sorted.higher(5)); // 8
        System.out.println(sorted.floor(4));  // 2

        // EnumSet - ultra-fast bit-vector set for enums
        Set<Day> weekend = EnumSet.of(Day.SATURDAY, Day.SUNDAY);
        Set<Day> weekdays = EnumSet.complementOf((EnumSet<Day>) weekend);
        System.out.println(weekdays);
    }
}

enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

