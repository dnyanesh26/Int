package org.example.Collections.Map;
import java.util.concurrent.ConcurrentHashMap;

class Test {

    static ConcurrentHashMap<Integer,String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for(int i=1;i<=5;i++)
                map.put(i,"Thread1-"+i);
        });

        Thread t2 = new Thread(() -> {
            for(int i=6;i<=10;i++)
                map.put(i,"Thread2-"+i);
        });

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch(Exception ignored){}

        System.out.println(map);
    }
}