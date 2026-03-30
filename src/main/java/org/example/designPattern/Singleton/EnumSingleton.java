package org.example.designPattern.Singleton;
/*

3. The Enum Singleton (The "Gold Standard")
According to Joshua Bloch (Effective Java), a single-element enum type is the best way to implement a Singleton.
It provides:
Absolute Thread Safety by default.
Serialization Guarantee: Java ensures the same instance is returned even after de-serialization.
Reflection Immunity: Java prevents the instantiation of enums via reflection.

1. Automatic JVM Allocation
When you declare an enum constant like INSTANCE,
the JVM treats it as a public static final field of the enum type.
The object is instantiated eagerly during class loading,
ensuring it is thread-safe and exists before any thread can access it.
 */


public enum EnumSingleton {
    INSTANCE;

    public void performAction() {
        System.out.println("Doing something safely...");
    }
}
