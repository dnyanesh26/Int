package org.example.MultiThreading.Problems.GPT;

public class ABC {

    private int state = 0;
    volatile private int rep = 3;
    private synchronized void print(char ch, int target)  {

        while (true){

                while (target != state % 3) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(rep==0) {
                    state++;
                    notifyAll();
                    return;
                }
                System.out.print(rep);
                System.out.print(ch);
                state++;
                if (target % 3 == 2) {
                    --rep;
                    System.out.println();
                }
                notifyAll();

        }
    }

    public static void main(String[] args) {
        ABC ch = new ABC();

        new Thread(()-> ch.print('A',0)).start();
        new Thread(()-> ch.print('B',1)).start();
        new Thread(()-> ch.print('C',2)).start();
    }
}
