package org.example.LinkedList;

public class DLImpl {

    public static void main(String[] args) {
        DoublyLinkedList<String> dl = new DoublyLinkedList<>();
        dl.addFirst("one");
        dl.addLast("two");
        dl.addFirst("three");

        dl.printForward();
        dl.printBackward();

        dl.removeFirst();
        dl.printForward();

        dl.removeLast();
        dl.printBackward();

        dl.addFirst("one");
        dl.addLast("two");
        dl.addFirst("three");
        dl.printForward();

        dl.add(3,"four");
        dl.add(0,"zero");
        dl.printForward();

        dl.add(3,"new");
        dl.printForward();
        dl.printBackward();

        dl.add(2,"start");
        dl.printForward();
        dl.printBackward();

        dl.add(4,"middle");
        dl.printForward();
        dl.printBackward();

        dl.add(4,"middleNew");
        dl.printForward();
        dl.printBackward();
    }
}
