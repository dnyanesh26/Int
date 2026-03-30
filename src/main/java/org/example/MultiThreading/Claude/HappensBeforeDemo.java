package org.example.MultiThreading.Claude;

public class HappensBeforeDemo {

    private int x = 0;
    private volatile boolean flag = false;

    // Writer thread
    public void writer() {
        x = 42;           // (1) Write to x
        flag = true;      // (2) Write to volatile flag — establishes happens-before
    }

    // Reader thread
    public void reader() {
        if (flag) {        // (3) Read volatile flag — if true...
            // ...then (1) happens-before (3), so x == 42 is guaranteed visible
            System.out.println(x); // Guaranteed to print 42
        }
    }

    // Without volatile on flag, (1) could be invisible to reader even if flag is true
    // due to CPU caching or instruction reordering

    public static void main(String[] args) throws InterruptedException {
        HappensBeforeDemo demo = new HappensBeforeDemo();

        Thread writer = new Thread(demo::writer);
        Thread reader = new Thread(demo::reader);

        writer.start();
        writer.join(); // join() happens-before anything after it
        // We're now guaranteed to see all writes made by writer thread
        reader.start();
        reader.join();
    }
}