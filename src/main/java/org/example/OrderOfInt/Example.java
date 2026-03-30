package org.example.OrderOfInt;

public class Example {
}
class Parent1 {
    static String parentStaticField = print("1. Parent: Static Variable");

    static {
        System.out.println("2. Parent: Static Block");
    }

    String parentInstanceField = print("5. Parent: Instance Variable");

    {
        System.out.println("6. Parent: Instance Block");
    }

    public Parent1() {
        System.out.println("7. Parent: Constructor");
    }

    public static String print(String message) {
        System.out.println(message);
        return message;
    }
}

class Child1 extends Parent1 {
    static String childStaticField = print("3. Child: Static Variable");

    static {
        System.out.println("4. Child: Static Block");
    }

    String childInstanceField = print("8. Child: Instance Variable");

    {
        System.out.println("9. Child: Instance Block");
    }

    public Child1() {
        // super(); is called implicitly here
        System.out.println("10. Child: Constructor");
    }

    public static void main(String[] args) {
        System.out.println("--- Starting Main ---");
        new Child1();
    }
}
