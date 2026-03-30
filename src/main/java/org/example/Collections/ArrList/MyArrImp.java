package org.example.Collections.ArrList;

import java.util.Arrays;

public class MyArrImp {

    public static void main(String[] args) {

        MyArrayList<String> list = new MyArrayList<>();
        list.add("queue");
        list.add("stack");

        System.out.println(Arrays.toString(list.toArray()));
    }
}
