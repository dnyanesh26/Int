package org.example.MultiThreading.Problems.GPT;


class Singleton{
    private static Singleton INSTANCE;

    private Singleton(){}

    public static Singleton getInstance(){
        if(INSTANCE!=null){
            synchronized (Singleton.class){
                if(INSTANCE!=null){
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}



public class ThreadSafeSingleton {

    public static void main(String[] args) {

    }
}
