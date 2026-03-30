package org.example.MultiThreading.synchExmp.synchBlock;

public class GirlFriend {
    private Object assistant = new Object();
    public void meet() {
        synchronized (assistant) {
            try {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " meeting started!");
                Thread.sleep(100);
                assistant.wait();
                System.out.println(threadName + " meeting ended!!");
            } catch (Exception e) {
            }
        }
    }

    public void meet2() {
        synchronized (assistant) {
            try {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " meeting 2 started!");
                Thread.sleep(100);
                assistant.notifyAll();
                System.out.println(threadName + " meeting 2 ended!!");
            } catch (Exception e) {
            }
        }
    }
}