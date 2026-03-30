package org.example.Collections.Map;

import java.util.Objects;

public class PersonObjComp {
    private String name;
    private int id;

    public PersonObjComp(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Getters and setters (omitted for brevity)

    @Override
    public boolean equals(Object obj) {
        // 1. Check for reference equality (same object instance)
        if (this == obj) {
            return true;
        }

        // 2. Check for null or different class type
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // 3. Cast the object to the correct type
        PersonObjComp person = (PersonObjComp) obj;

        // 4. Compare significant fields
        // Use Objects.equals() for object fields (handles nulls safely)
        // Use == for primitive fields
        return id == person.id && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        // Use the same significant fields as in equals()
        // Objects.hash() is a utility method that generates a good hash code
        return Objects.hash(name, id);
    }
}
