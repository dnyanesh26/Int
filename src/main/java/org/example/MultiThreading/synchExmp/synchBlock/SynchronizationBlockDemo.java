package org.example.MultiThreading.synchExmp.synchBlock;
//synchronized(this) is similar to a synchronized method
public class SynchronizationBlockDemo {
    private static class BoyFriendThread extends Thread {
        @Override
        public void run() {
            girlFriend.meet();

        }
    }
    private static class BoyFriendThread2 extends Thread {
        @Override
        public void run() {
            girlFriend.meet2();

        }
    }
    private static final GirlFriend girlFriend = new GirlFriend();
    public static void main(String[] args) {
//        for (int counter = 0; counter < 10; ++counter) {
//            BoyFriendThread fThread = new BoyFriendThread();
//            fThread.setName("BoyFriend-" + counter);
//            fThread.start();
//        }

        BoyFriendThread fThread = new BoyFriendThread();
            fThread.setName("BoyFriend-" + 1);
            fThread.start();

        BoyFriendThread2 fThread2 = new BoyFriendThread2();
        fThread2.setName("BoyFriend-" + 2);
        fThread2.start();
    }
}