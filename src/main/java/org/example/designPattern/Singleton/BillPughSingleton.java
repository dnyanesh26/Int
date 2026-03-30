package org.example.designPattern.Singleton;
/*

2. The "Bill Pugh" Singleton (Recommended)
This uses the Java Classloader mechanism to guarantee thread safety without using synchronized keywords.
The inner class is only loaded when getInstance() is called.

 */


import java.io.Serial;
import java.io.Serializable;

public class BillPughSingleton implements Serializable {
    private BillPughSingleton() {}

    private static class SingletonHelper {
        // Final ensures it's constant; Classloader ensures thread safety
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Serial
    protected Object readResolve() {
        return SingletonHelper.INSTANCE;
    }
}
