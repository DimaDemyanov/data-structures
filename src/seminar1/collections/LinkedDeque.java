package seminar1.collections;

import java.util.Iterator;

public class LinkedDeque<Item> implements IDeque<Item> {

    // -> [tail -> .. -> .. -> head] ->
    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    @Override
    public void pushFront(Item item) {
        /* TODO: implement it */
        Node<Item> newNode = new Node<Item>(item, tail, null);
        if(tail != null)
            tail.prev = newNode;
        tail = newNode;
        if(size == 0)
            head = tail;
        size++;
    }

    @Override
    public void pushBack(Item item) {
        /* TODO: implement it */
        Node<Item> newNode = new Node<>(item, null, head);
        if( head != null)
            head.next = newNode;
        head = newNode;
        if(size == 0)
            tail = head;
        size++;
    }

    @Override
    public Item popFront() {
        /* TODO: implement it */
        Item ret = tail.item;
        tail = tail.next;
        if (tail != null)
            tail.prev = null;
        size = size - (size > 0 ? 1 : 0);
        return ret;
    }

    @Override
    public Item popBack() {
        /* TODO: implement it */
        Item ret = head.item;
        head = head.prev;
        if (head != null)
            head.next = null;
        size = size - (size > 0 ? 1 : 0);
        return ret;
    }

    @Override
    public boolean isEmpty() {
        /* TODO: implement it */
        if (size > 0) return true;
        return false;
    }

    @Override
    public int size() {
        /* TODO: implement it */
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        /* TODO: implement it */
        return new LinkedDequeueIterator();
    }

    private class LinkedDequeueIterator implements Iterator<Item> {

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
        Node<Item> next, prev;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next, Node<Item> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public static void main(String[] args) {
        LinkedDeque<Integer> queue = new LinkedDeque<>();
        for (int i = 0; i < 10; i++) {
            queue.pushFront(i);
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(queue.popFront() + "iaaa");
        }
        Iterator<Integer> it = queue.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

}
