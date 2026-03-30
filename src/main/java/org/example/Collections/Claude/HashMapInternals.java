package org.example.Collections.Claude;

import java.util.*;

public class HashMapInternals {
    public static void main(String[] args) {
        // Default capacity=16, load factor=0.75 -> resize at 12 entries
        HashMap<String, Integer> map = new HashMap<>(16, 0.75f);

        // Deliberate hash collisions (same bucket)
        // 'Aa' and 'BB' have the same hashCode in Java
        System.out.println("Aa".hashCode());   // 2112
        System.out.println("BB".hashCode());   // 2112

        map.put("Aa", 1);
        map.put("BB", 2);
        // Both land in same bucket -> linked list

        // Pre-size to avoid rehashing if cardinality known
        int expectedSize = 1000;
        HashMap<String, Integer> optimized =
                new HashMap<>((int)(expectedSize / 0.75) + 1);

        // Custom key: MUST override both hashCode and equals
        Map<Point, String> pointMap = new HashMap<>();
        pointMap.put(new Point(1, 2), "origin");
    }
}

class Point {
    final int x, y;
    Point(int x, int y) { this.x = x; this.y = y; }

    @Override public int hashCode() {
        return Objects.hash(x, y); // 31*x + y pattern
    }
    @Override public boolean equals(Object o) {
        if (!(o instanceof Point)) return false;
        Point p = (Point) o;
        return x == p.x && y == p.y;
    }
}
