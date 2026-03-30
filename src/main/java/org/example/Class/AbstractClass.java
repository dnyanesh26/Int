package org.example.Class;

public class AbstractClass {

}

abstract class SmartDevice {
    // 1. Instance Variable (State)
    protected int batteryLevel;

    // 2. Constructor (Abstract classes can have them!)
    SmartDevice(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    // 3. Abstract Method (Must be implemented by Phone, Tablet, etc.)
    abstract void performPrimaryFunction();

    // 4. Concrete Method (Shared logic)
    void showBattery() {
        System.out.println("Battery at: " + batteryLevel + "%");
    }

    // 5. Final Method (Subclasses cannot change this security check)
    final void bootUp() {
        System.out.println("System integrity check passing...");
        System.out.println("Device is ON.");
    }
}

class Smartphone extends SmartDevice {
    Smartphone(int battery) {
        super(battery);
    }

    @Override
    void performPrimaryFunction() {
        System.out.println("Making a phone call...");
    }

    // concrete method can be overriden
//    void showBattery(){
//        System.out.println("Battery: "+batteryLevel);
//    }


    public static void main(String[] args) {
        Smartphone sm =new Smartphone(100);
        sm.showBattery();
        sm.bootUp();
    }
}