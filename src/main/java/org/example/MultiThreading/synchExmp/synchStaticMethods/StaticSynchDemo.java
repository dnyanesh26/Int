package org.example.MultiThreading.synchExmp.synchStaticMethods;

public class StaticSynchDemo {
    private static class BoyFriend1 extends Thread {
        @Override
        public void run() {
            GirlFriend.sing();
        }
    }
    private static class BoyFriend2 extends Thread {
        @Override
        public void run() {
            GirlFriend.count();
        }
    }
    public static void main(String args[]) {
        new BoyFriend1().start();
        new BoyFriend2().start();
    }
}