package org.example.DSA.Wissen;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class HighestAverage {
    static class Student {
        String name;
        List<Integer> scores;
        Student(String name) {
            this.name = name;
            this.scores = new ArrayList<>();
        }
        double getAverage() {
            return scores.stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0.0);
        }
    }
    public String findHighestAverage(String[][] scores) {
        Map<String,Student> scoreMap = new HashMap<>();
        for(String[] student: scores){
            scoreMap.putIfAbsent(student[0],new Student(student[0]));
            scoreMap.get(student[0]).scores.add(Integer.parseInt(student[1]));
        }
        AtomicReference<Double> maxAvg= new AtomicReference<>((double) 0);
        AtomicReference<String> name = new AtomicReference<>();
        scoreMap.forEach((key, value) -> {
            if (maxAvg.get() < value.getAverage()) {
                maxAvg.set(value.getAverage());
                name.set(key);
            }
        });
        return name.get();
    }

    public static void main(String[] args) {
        HighestAverage solution = new HighestAverage();
        String[][] scores = {
                {"Alice", "85"}, {"Bob", "90"},
                {"Alice", "95"}, {"Bob", "85"}
        };
        System.out.println(solution.findHighestAverage(scores));
        // Output: Alice (Average: 90)
    }
}
