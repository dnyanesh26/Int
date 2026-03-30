package org.example.DSA.Claude;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class MergeSortedList {


    ListNode merge2ListNodeNaive(ListNode l1, ListNode l2){

        ListNode current = new ListNode(0);
        ListNode dummy = current;

        while(l1!=null && l2!=null){
            if(l1.val<=l2.val){
                current.next=new ListNode(l1.val);
                l1=l1.next;
            }
            else{
                current.next=new ListNode(l2.val);
                l2=l2.next;

            }
            current=current.next;
        }

        while(l1!=null){
            current.next=new ListNode(l1.val);
            l1=l1.next;
            current=current.next;
        }

        while(l2!=null){
            current.next=new ListNode(l2.val);
            l2=l2.next;
            current=current.next;
        }

        return dummy.next;

    }

    ListNode merge2ListNodeRecursive(ListNode l1, ListNode l2){

        if(l1==null) return l2;
        if(l2==null) return l1;

        if(l1.val<=l2.val){
            l1.next=merge2ListNodeRecursive(l1.next, l2);
            return l1;
        }
        else{
            l2.next=merge2ListNodeRecursive(l1,l2.next);
            return l2;
        }
    }

    ListNode merge2ListNodeOptimized(ListNode l1, ListNode l2){

        ListNode current = new ListNode(0);
        ListNode dummy = current;

        while(l1!=null && l2!=null){
            if(l1.val<=l2.val){
                current.next=l1;
                l1=l1.next;
            }
            else{
                current.next=l2;
                l2=l2.next;
            }
            current=current.next;

        }

        if(l1!=null){
            current.next=l1;
        }
        if(l2!=null){
            current.next=l2;
        }

        return dummy.next;
    }

    public static void traverseList(ListNode head) {
        ListNode current = head; // Start at the head of the list

        while (current != null) {
            System.out.print(current.val + " "); // Process the data (e.g., print)
            current = current.next; // Move to the next node
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Create List 1: 1 -> 3 -> 5
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(3);
        list1.next.next = new ListNode(5);

        // Create List 2: 2 -> 4 -> 6
        ListNode list2 = new ListNode(2);
        list2.next = new ListNode(4);
        list2.next.next = new ListNode(6);

        // Use your logic (like merging or printing) here

//        ListNode list3= new MergeSortedList().merge2ListNodeNaive(list1,list2);
//        ListNode list3= new MergeSortedList().merge2ListNodeOptimized(list1,list2);
        ListNode list3= new MergeSortedList().merge2ListNodeRecursive(list1,list2);
        System.out.print("Linked List: ");
        traverseList(list3);
    }
}
