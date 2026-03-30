package org.example.OrderOfInt;

class Parent {

    static int pStatic = initialize("Parent Static Var");
    static {
        System.out.println("Parent Static Block");
    }

    int pInstance = initialize("Parent Instance Var");



    Parent() {
        System.out.println("Parent Constructor");
    }
    {
        System.out.println("Parent Instance Block");
    }

    static int initialize(String message) {
        System.out.println(message);
        return 0;
    }
}

class Child extends Parent {
    static int cStatic = initialize("Child Static Var");

    static {
        System.out.println("Child Static Block");
    }

    int cInstance = initialize("Child Instance Var");

    {
        System.out.println("Child Instance Block");
    }

    Child() {
        super(); // Implicit
        System.out.println("Child Constructor");
    }

    public static void main(String[] args) {
        System.out.println("--- Starting Main ---");
        new Child();
    }
}

