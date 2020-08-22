package helperClasses;

import Exceptions.EmptyListException;

public class NodeLinkedList {
    public Node head = null;
    private int size = 0;

    public NodeLinkedList() {
    }

    public NodeLinkedList(Node head) {
        this.head = head;
    }

    public void addFirst(Node node) {
        if(head == null) {
            head = node;
            size++;
            return;
        }

        node.next = head;
        head = node;
        size++;
    }

    public void addLast(Node node) {
        Node p = head;

        if(p == null) {
            head = node;
            size++;
            return;
        }

        while(p.next != null)
            p = p.next;

        p.next = node;
        size++;
    }

    public void add(Node node, int index) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException();

        if(index == 0)
            addFirst(node);

        else if(index == size-1 || index == size)
            addLast(node);

        else {
            Node p = head;
            for (int i=0 ; i<index-1 ; i++)
                p = p.next;

            node.next = p.next.next;
            p.next = node;
        }

        size++;
    }

    public int getSize() {
        return size;
    }

    public Node removeFirst() {
        if(head == null)
            throw new EmptyListException();

        else if (head.next == null) {
            Node node = head;
            head = null;
            size--;
            return node;
        }

        else {
            Node node = head;
            head = head.next;
            size--;
            return node;
        }
    }

    public Node removeLast() {
        if (head == null)
            throw new EmptyListException();

        else if(head.next == null) {
            Node node = head;
            head = null;
            size--;
            return node;
        }

        else {
            Node p = head;
            while(p.next.next != null) {
                p = p.next;
            }

            Node node = p.next;
            p.next = null;
            size--;
            return node;
        }

    }

    public Node remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException();

        if(index == 0)
            return removeFirst();

        else if(index == size-1)
            return removeLast();

        else {
            Node p = head;
            for (int i=0 ; i<index-1 ; i++)
                p = p.next;

            size--;
            Node res = p.next;
            p.next = p.next.next;
            return res;
        }
    }


    public Node get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException();

        if(index == 0)
            return getFirst();

        else if(index == size-1)
            return getLast();

        else {
            Node p = head;
            for (int i=0 ; i<index-1 ; i++)
                p = p.next;

            return p.next;
        }
    }

    public Node getLast() {
        if (head == null)
            throw new EmptyListException();

        else if(head.next == null) {
            return head;
        }

        else {
            Node p = head;
            while(p.next.next != null) {
                p = p.next;
            }

            return p.next;
        }

    }

    public Node getFirst() {
        if(head == null)
            throw new EmptyListException();

        return head;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String getValue(String key) {
        for (int i=0 ;i<getSize() ; i++){
            Node p = this.get(i);
            if (p.key.equals(key))
                return p.value.toString().trim();
        }

        return null;
    }

    public String getKey(String value) {
        for (int i=0 ;i<getSize() ; i++){
            Node p = this.get(i);
            if (p.value.toString().equals(value)) {
                System.out.print(p.key);
                return p.key.toString();
            }
        }

        return null;
    }


//    public static void main(String[] args) {
//        NodeLinkedList list = new NodeLinkedList();
//        list.addFirst(new Node("hsfd", "5246"));
//        list.addFirst(new Node("hsfd", "46"));
//        list.addFirst(new Node("hsfd", "6"));
//        list.addFirst(new Node("hsfd", "554"));
//        list.addFirst(new Node("\r", "72"));
//        list.addFirst(new Node("\n", "546"));
//
//        while (!list.isEmpty()) {
//            System.out.println(list.removeLast());
//        }
//    }
}


