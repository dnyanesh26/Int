package org.example.Queue;

public class ArrayQueue {
    private int[] queueArray;
    private int front;
    private int rear;
    private int capacity;
    private int currentSize;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        queueArray = new int[capacity];
        front = 0;
        rear = -1;
        currentSize = 0;
    }

    public void enqueue(int item) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot enqueue " + item);
            return;
        }

        rear++;
        queueArray[rear] = item;
        currentSize++;
        System.out.println(item + " enqueued to queue");
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return -1; // Or throw an exception
        }
        int item = queueArray[front];
        front++;
        currentSize--;
        System.out.println(item + " dequeued from queue");
        return item;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty. No element to peek.");
            return -1;
        }
        return queueArray[front];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == capacity;
    }

    public int size() {
        return currentSize;
    }
}
