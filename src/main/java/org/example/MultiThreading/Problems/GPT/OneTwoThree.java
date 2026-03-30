package org.example.MultiThreading.Problems.GPT;

public class OneTwoThree {
    private int state = 1;
    private int rep = 3;
    private synchronized void print(int i){

        while(true){
            while(i%3!=state%3){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(i==3){
                rep--;

                if(rep==0) {
                    System.out.print(i);
                    state++;
                    notifyAll();
                    return;
                }
            }
            if(rep==0) {
                state++;
                notifyAll();
                return;
            }

            System.out.print(i);
            state++;
            notifyAll();
        }
    }

    public static void main(String[] args) {
        OneTwoThree one = new OneTwoThree();

        new Thread(()-> one.print(1)).start();
        new Thread(()-> one.print(2)).start();
        new Thread(()-> one.print(3)).start();
    }
}
