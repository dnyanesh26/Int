package org.example.MultiThreading.synchExmp.synchMethods;

public class DiffObjectSynchDemo {
    private static class BoyFriend extends Thread {
        private GirlFriend girlFriend;
        public BoyFriend(GirlFriend girlFriend) {
            this.girlFriend = girlFriend;
        }
        @Override
        public void run() {
            girlFriend.meet();
        }
    }
    public static void main(String args[]) {
        GirlFriend girlFriend1 = new GirlFriend("GirlFriend1");
        BoyFriend boyFriend1 = new BoyFriend(girlFriend1);
        boyFriend1.setName("BoyFriend1");
        boyFriend1.start();

        GirlFriend girlFriend2 = new GirlFriend("GirlFriend2");
        //if we comment out this line and use only one gf object then it will work as static synchronized

        BoyFriend boyFriend2 = new BoyFriend(girlFriend2);
        boyFriend2.setName("BoyFriend2");
        boyFriend2.start();
    }
}
