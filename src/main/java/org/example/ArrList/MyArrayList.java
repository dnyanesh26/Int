package org.example.ArrList;

public class MyArrayList<E> {
    private E[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        this.data = (E[]) new Object[initialCapacity];
        this.size = 0;
    }

    public void add(E element) {
        if (size == data.length) {
            resize();
        }
        data[size++] = element;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return data[index];
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E removedElement = data[index];
        // Shift elements to the left
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = null; // Clear last element and decrement size
        return removedElement;
    }

    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = data.length * 2;
        E[] newData = (E[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);//IMP
        this.data = newData;
    }

    public E[] toArray(){
        return data;
    }
}
