package org.example.MultiThreading.synchExmp.synchStaticMethods;

public class GirlFriend {
    public static synchronized void sing() {

        try {
            for(int i = 1; i <= 10; ++i) {
                System.out.println("lullaby");
                Thread.sleep(100);
            }
        } catch (Exception e) {
        }
    }

    public static synchronized void count() {

        try {
            for(int i = 1; i <= 10; ++i) {
                System.out.println(i);
                Thread.sleep(100);
            }
        } catch (Exception e) {
        }
    }
}