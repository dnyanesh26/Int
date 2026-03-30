package org.example.MultiThreading.Problems.GPT;

import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {

    private ReentrantLock[] forks = new ReentrantLock[5];

    DiningPhilosophers(){
        for(int i=0;i<5;i++){
            forks[i]=new ReentrantLock();
        }
    }

    public void eat(int i){
        int left = i, right = (i+1)%5;
        while(true) {
            try {
                if (forks[left].tryLock() && forks[right].tryLock()) {
                    System.out.println("Philosopher " + i + " is eating");
                    return;
                }
            } finally {
                if (forks[left].isHeldByCurrentThread()) forks[left].unlock();
                if (forks[right].isHeldByCurrentThread()) forks[right].unlock();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {

        DiningPhilosophers dPh = new DiningPhilosophers();
        for (int i=1;i<5;i++){
            int id = i;
            new Thread(() -> dPh.eat(id)).start();
        }
    }
}
