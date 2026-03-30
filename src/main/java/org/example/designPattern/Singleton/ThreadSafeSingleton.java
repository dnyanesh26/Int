package org.example.designPattern.Singleton;

/*
1. The "Double-Checked Locking" Approach
This is the classic way to ensure thread safety while maintaining performance by avoiding
unnecessary synchronization after the instance is created.
*/

import java.io.Serial;
import java.io.Serializable;

public class ThreadSafeSingleton implements Serializable {
    // 'volatile' is crucial: it ensures changes are visible across threads
    // and prevents instruction reordering.
    private static volatile ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
        // Protection against Reflection API
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to create");
        }
    }

    public static ThreadSafeSingleton getInstance() {
        if (instance == null) { // First check (no locking)
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) { // Second check (locked)
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }

    @Serial
    protected Object readResolve() {
        return instance;
    }
}
