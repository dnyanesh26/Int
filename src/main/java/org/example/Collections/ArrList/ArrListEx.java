package org.example.Collections.ArrList;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrListEx {

    public static void main(String[] args) {
//        The java.util.ArrayList class provides a dynamic array implementation in Java, offering various methods for manipulating its elements. Here are some of the most commonly used methods with code examples:
//        add(E e): Appends the specified element to the end of this list.
 

        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
//        add(int index, E element): Inserts the specified element at the specified position in this list.

        list.add(0, "Orange"); // Inserts "Orange" at index 0
        list.add("Grape");

//        remove(int index): Removes the element at the specified position in this list.
        System.out.println(Arrays.toString(list.toArray()));
        list.remove(1); // Removes "Apple"

//        remove(Object o): Removes the first occurrence of the specified element from this list, if it is present.
        list.remove("Orange");
        System.out.println(Arrays.toString(list.toArray()));
        list.remove("Banana");

//        get(int index): Returns the element at the specified position in this list.
        list.add(0, "Apple");
        String fruit = list.get(0); // fruit will be "Apple"
        System.out.println(fruit);

//        set(int index, E element): Replaces the element at the specified position in this list with the specified element.

        list.set(1, "Banana"); // Replaces "Orange" with "Banana"
//        size(): Returns the number of elements in this list.
        System.out.println(Arrays.toString(list.toArray()));
        int count = list.size(); // count will be 2
        System.out.println(count);

//        contains(Object o): Returns true if this list contains the specified element.
        boolean hasApple = list.contains("Apple"); // hasApple will be true
        System.out.println(hasApple);

//        indexOf(Object o): Returns the index of the first occurrence of the specified element in this list,
//        or -1 if this list does not contain the element.
        System.out.println(Arrays.toString(list.toArray()));
        int index = list.indexOf("Orange"); // index will be -1
        System.out.println(index);
        index = list.indexOf("Apple"); // index will be 0
        System.out.println(index);
//        clear(): Removes all of the elements from this list.
        System.out.println(Arrays.toString(list.toArray()));


        list.clear(); // list will be empty
//        isEmpty(): Returns true if this list contains no elements.
        System.out.println(Arrays.toString(list.toArray()));


        boolean empty = list.isEmpty(); // empty will be true
//        trimToSize(): Trims the capacity of this ArrayList instance to be the list's current size.
        System.out.println(Arrays.toString(list.toArray()));

        ArrayList<String> listcap = new ArrayList<>(10); // Initial capacity 10

        listcap.add("Item1");
        listcap.trimToSize(); // Reduces capacity to 1

//        toArray(): Returns an array containing all of the elements in this list in proper sequence (from first to last element).
        System.out.println(Arrays.toString(list.toArray()));

        ArrayList<String> listObj = new ArrayList<>(Arrays.asList("Apple", "Orange"));
        Object[] array = listObj.toArray();
    }
}
