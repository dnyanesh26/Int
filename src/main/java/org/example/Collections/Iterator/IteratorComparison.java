package org.example.Collections.Iterator;

import java.util.*;

public class IteratorComparison {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("Java", "Python", "C++"));

        // 1. Iterator: Simple forward-only traversal
        System.out.println("--- Iterator (Forward Only) ---");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String lang = iterator.next();
            System.out.println("Language: " + lang);

            // Can remove, but cannot add or set
            if (lang.equals("C++")) {
                iterator.remove();
            }
        }

        // 2. ListIterator: Bi-directional and Modification
        System.out.println("\n--- ListIterator (Bi-directional) ---");
        ListIterator<String> listIterator = list.listIterator();

        // Move forward and modify
        while (listIterator.hasNext()) {
            String lang = listIterator.next();
            if (lang.equals("Java")) {
//                listIterator.set("JavaScript"); // Updates element
                listIterator.add("Kotlin");     // Inserts new element
            }
        }

        // Move backward (only possible with ListIterator)
        System.out.println("Traversing Backwards:");
        while (listIterator.hasPrevious()) {
            System.out.println("Index " + listIterator.previousIndex() + ": " + listIterator.previous());
        }

        System.out.println("\nFinal List: " + list);
    }
}

