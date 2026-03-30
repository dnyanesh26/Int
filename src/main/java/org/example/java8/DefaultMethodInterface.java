package org.example.java8;

interface Vehicle {
    // Abstract method
    void start();

    // Default method
    default void honk() {
        System.out.println("Beep beep!");
    }

    // Static method
    static void checkService() {
        System.out.println("Service check");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car starting");
    }
    // honk() is inherited, no need to implement
}


public class DefaultMethodInterface {

    public static void main(String[] args) {
        Car car = new Car();
        car.start();  // Car starting
        car.honk();   // Beep beep!
        Vehicle.checkService(); // Service check
    }
}
