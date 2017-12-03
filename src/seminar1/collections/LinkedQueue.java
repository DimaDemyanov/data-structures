package seminar1.collections;

import java.util.Iterator;

public class LinkedQueue<Item> implements IQueue<Item> {

    // -> [tail -> .. -> .. -> head] ->
    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    @Override
    public void enqueue(Item item) {
        /* TODO: implement it */
        Node<Item> newNode = new Node<Item>(item, null);
        if( head != null)
            head.next = newNode;
        head = newNode;
        if(size == 0)
            tail = head;
        size++;
    }

    @Override
    public Item dequeue() {
        /* TODO: implement it */
        Item ret = tail.item;
        tail = tail.next;
        size = size - (size > 0 ? 1 : 0);
        return ret;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<Item> {

        Node<Item> t = tail;

        @Override
        public boolean hasNext() {
            /* TODO: implement it */
            if(t != null) return true;
            return false;
        }

        @Override
        public Item next() {
            /* TODO: implement it */
            Item ret = t.item;
            t = t.next;
            return ret;
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(queue.dequeue() + "iaaa");
        }
        Iterator<Integer> it = queue.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
