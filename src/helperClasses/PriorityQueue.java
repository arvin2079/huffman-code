package helperClasses;

import Exceptions.EmptyListException;

public class PriorityQueue {
    public Node head;
    private int size;

    public PriorityQueue(String key , String value) {
        head = new Node(key , value);
        size++;
    }

    public PriorityQueue() {}

    public int getSize() {
        return size;
    }

    public void enqueue(Node element) {
        if(head == null) {
            head = element;

        }else if(element.value.compareTo(head.value) <= 0){
            element.next = head;
            head = element;

        }else if(element.value.compareTo(head.value) == 1 ){
            Node p = head;
            while(p.next != null && element.value.compareTo(p.next.value) == 1)
                p = p.next;

            if(p.next == null)
                p.next = element;
            else {
                element.next = p.next;
                p.next = element;
            }
        }

        size ++;
    }

    public Node dequeue() {
        if (isEmpty())
            throw new EmptyListException();

        if (head.next == null) {
            Node p = head;
            head = null;
            size--;
            return p;
        }

        Node p = head;
        head = head.next;
        size--;
        return p;
    }

    public Node peek() {
        return head;
    }

    public boolean isEmpty() {
        return size == 0;
    }

//    public static void main(String[] args) {
//        PriorityQueue q = new PriorityQueue();
//        q.enqueue(new Node("hfs", "1523"));
//        q.enqueue(new Node("gshfs", "230"));
//        q.enqueue(new Node("hfts", "3"));
//        q.enqueue(new Node("hfts", "4"));
//        q.enqueue(new Node("hfts", "454"));
//        q.enqueue(new Node("hfts", "45"));
//        q.enqueue(new Node("hfts", "45"));
//        q.enqueue(new Node("hfts", "452"));
//        q.enqueue(new Node("hfts", "481"));
//        q.enqueue(new Node("hfts", "48"));
//        q.enqueue(new Node("hfts", "18"));
//        q.enqueue(new Node("hfts", "8"));
//        q.enqueue(new Node("hfts", "0"));
//        q.enqueue(new Node("hfts", "4"));
//        q.enqueue(new Node("hfts", "40165"));
//        q.enqueue(new Node("hfts", "4789"));
//        q.enqueue(new Node("hfes", "14523"));
//        q.enqueue(new Node("hfcs", "12"));
//
//        int sizes = q.getSize();
//        for (int i=0 ; i<sizes ; i++) {
//            System.out.println(q.dequeue());
//        }
//    }


}

