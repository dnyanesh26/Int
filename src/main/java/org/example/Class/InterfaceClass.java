package org.example.Class;

public class InterfaceClass {

}

interface Vehicle {
    // 1. Variable (Constant)
    int MAX_SPEED = 120;

    // 2. Abstract Method (The 'What')
    //All methods in a Java interface are considered public by default
    void move();

    // 3. Default Method (The 'How' - provided by default) java 8
    default void stop() {
        applyBrakes();

        System.out.println("Vehicle has stopped.");
    }

    // 4. Static Method (Utility) java 8
    static boolean isSpeedValid(int speed) {

        return speed <= MAX_SPEED;

    }

    // 5. Private Method (Internal Logic) java 9
    private void applyBrakes() {
        System.out.println("Applying internal braking logic...");
    }
}

class Car implements Vehicle {
    @Override
    public void move() {
        System.out.println("The car is driving on four wheels.");
    }

//     default can be overriden
//    public void stop(){
//        System.out.println("Vehicle is stopping");
//    }

    public static void main(String[] args) {
        System.out.println(Vehicle.isSpeedValid(100));
        new Car().stop();
    }
}