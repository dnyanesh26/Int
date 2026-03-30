package org.example.Collections.Claude;

import java.util.*;

public class SpecialMapsDemo {
    public static void main(String[] args) throws Exception {

        // WeakHashMap - automatic eviction when key is GC'd
        WeakHashMap<Object, String> cache = new WeakHashMap<>();
        Object key1 = new Object();
        Object key2 = new Object();
        cache.put(key1, "value1");
        cache.put(key2, "value2");
        System.out.println("Before GC: " + cache.size()); // 2

        key1 = null; // remove strong reference
        System.gc();
        Thread.sleep(100);
        System.out.println("After GC: " + cache.size()); // may be 1

        // IdentityHashMap - keys compared by reference (==), not equals()
        IdentityHashMap<String, String> idMap = new IdentityHashMap<>();
        String s1 = new String("hello");
        String s2 = new String("hello"); // different object, same content
        idMap.put(s1, "first");
        idMap.put(s2, "second");
        idMap.put("hello","three");
        idMap.put("hello","four");
        System.out.println(idMap.size()); // 3 - different references!
        System.out.println(idMap);

        // Regular HashMap would have size=1 (equals() treats them as same key)
        HashMap<String, String> regular = new HashMap<>();
        regular.put(s1, "first");
        regular.put(s2, "second");
        System.out.println(regular.size()); // 1

        // EnumMap - fastest map for enum keys
        EnumMap<DayOfWeek, String> schedule = new EnumMap<>(DayOfWeek.class);
        schedule.put(DayOfWeek.MONDAY, "Standup at 9am");
        schedule.put(DayOfWeek.FRIDAY, "Retro at 4pm");
        // Internally: array[0]=Mon, array[1]=Tue... O(1) access
        System.out.println(schedule.get(DayOfWeek.MONDAY));
    }
}

enum DayOfWeek { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

