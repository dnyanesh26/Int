package org.example.LinkedList;

public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Add a node to the front of the list
    public void addFirst(T data) {
        Node newNode = new Node(data);
        if(head==null){
            head=newNode;
            tail=newNode;
        }
        else{
            newNode.next=head;
            head.prev=newNode;
            head=newNode;
        }
        size++;
    }

    // Add a node to the end of the list
    public void addLast(T data) {
        Node newNode = new Node(data);
        if(tail==null){
            head=newNode;
            tail=newNode;
        }
        else{
            newNode.prev=tail;
            tail.next=newNode;
            tail=newNode;
        }
        size++;

    }

    // Remove the first node
    public T removeFirst() {
        if(head==null){
            return null;
        }
        T data = head.data;
        if(head==tail){
            head=null;
            tail=null;
        }
        else{
            head=head.next;
            head.prev=null;
        }
        size--;
        return data;
    }

    // Remove the last node
    public T removeLast() {
        if(head==null){
            return null;
        }
        T data = tail.data;
        if(head==tail){
            head=null;
            tail=null;
        }
        else{
            tail=tail.prev;
            tail.next=null;
        }
        size--;
        return data;
    }

    // Get the size of the list
    public int size() {
        return size;
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Print the list from head to tail
    public void printForward() {

        Node currentNode = head;
        System.out.print("[");
        while(currentNode!=null){
            System.out.print(" "+currentNode.data+",");
            currentNode=currentNode.next;
        }
        System.out.print("]");
        System.out.println();

    }

    // Print the list from tail to head
    public void printBackward() {

        Node currentNode = tail;
        System.out.print("[");
        while(currentNode!=null){
            System.out.print(" "+currentNode.data+",");
            currentNode=currentNode.prev;
        }
        System.out.print("]");
        System.out.println();

    }

    public void add(int index, T data) {
        if (index>size-1){
            throw new IndexOutOfBoundsException();
        }
        if(index==0){
            addFirst(data);
        }
        else if(index==size-1){
            addLast(data);
        }
        else if(index>((size-1)/2)){
            addfromLast(index,data);
        }
        else{
            addfromStart(index,data);
        }


    }

    private void addfromLast(int index, T data) {
        System.out.println("addfromLast called");
        int currentIndex = size-1;
        Node currentNode = tail;
        while(currentIndex >= index){
            currentIndex--;
            currentNode=currentNode.prev;

        }
        Node newNode = new Node(data);
        currentNode.next.prev=newNode;
        newNode.next=currentNode.next;
        newNode.prev=currentNode;
        currentNode.next=newNode;
        size++;
    }

    private void addfromStart(int index, T data) {
        System.out.println("addfromStart called");
        int currentIndex = 0;
        Node currentNode = head;
        while(currentIndex < index -1){
            currentNode=currentNode.next;
            currentIndex++;
        }
        Node newNode = new Node(data);
        currentNode.next.prev=newNode;
        newNode.next=currentNode.next;
        newNode.prev=currentNode;
        currentNode.next=newNode;
        size++;
    }
}
