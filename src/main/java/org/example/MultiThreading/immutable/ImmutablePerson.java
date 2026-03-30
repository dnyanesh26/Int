package org.example.MultiThreading.immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ImmutablePerson {
    private final String name;
    private final List<String> hobbies;

    public ImmutablePerson(String name, List<String> hobbies) {
        this.name = name;
        // Rule 4: Defensive copy of the input list
        this.hobbies = new ArrayList<>(hobbies);
    }

    public String getName() {
        return name;
    }

    public List<String> getHobbies() {
        // Rule 5: Return an unmodifiable view to protect the internal list
        return Collections.unmodifiableList(hobbies);
    }
}