package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ImmutableStudent {
    private final String name;
    private final List<String> grades; // Mutable object

    public ImmutableStudent(String name, List<String> grades) {
        this.name = name;
        // DEFENSIVE COPY: Don't store the original list reference
        this.grades = new ArrayList<>(grades);
    }

    public String getName() { return name; }

    public List<String> getGrades() {
        // DEFENSIVE COPY: Don't return the internal list reference
        return Collections.unmodifiableList(new ArrayList<>(grades));
    }
}
