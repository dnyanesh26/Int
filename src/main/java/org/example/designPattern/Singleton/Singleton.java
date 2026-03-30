package org.example.designPattern.Singleton;
/*
The Singleton Pattern is a creational design pattern that
 ensures a class has only one instance while providing a global access point to that instance.

Key Concepts
Private Constructor: Prevents other objects from using the new operator with the Singleton class.
Static Instance: A private static variable that holds the unique instance of the class.
Static Access Method: A public static method (often named getInstance()) that returns the instance,
 creating it if it doesn't already exist.
*/

import java.io.Serial;
import java.io.Serializable;

public class Singleton implements Serializable {
    private static Singleton instance;

    // Private constructor prevents external instantiation
    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    @Serial
    protected Object readResolve() {
        return instance;
    }
}
