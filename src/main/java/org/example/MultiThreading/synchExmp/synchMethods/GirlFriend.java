package org.example.MultiThreading.synchExmp.synchMethods;

public class GirlFriend {
    private String girlFriendName;

    public GirlFriend(String name) {
        this.girlFriendName = name;
    }

    public synchronized void meet() {
        String boyFriendName = Thread.currentThread().getName();
        System.out.println(boyFriendName + " - " + girlFriendName + " meeting started");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        System.out.println(boyFriendName + " - " + girlFriendName + " meeting completed");
    }
}
