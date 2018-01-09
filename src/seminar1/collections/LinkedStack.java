package seminar1.collections;

import java.util.Iterator;

public class LinkedStack<Item> implements IStack<Item> {

    private Node<Item> head;
    private int size;

    @Override
    public void push(Item item) {
        /* TODO: implement it */
        Node<Item> newNode = new Node<Item>(item, head);
        head = newNode;
        size++;
    }

    @Override
    public Item pop() {
        /* TODO: implement it */
        Node<Item> ret = head;
        head = head.next;
        size = size - (size > 0 ? 1 : 0);
        return ret.item;
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
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<Item> {

        private Node<Item> h = head;

        @Override
        public boolean hasNext() {
            /* TODO: implement it */
            if(h != null) return true;
            return false;
        }

        @Override
        public Item next() {
            /* TODO: implement it */
            Node<Item> ret = h;
            h = h.next;
            return ret.item;
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 3; i++) {
            /*System.out.println*/stack.pop();
        }
        Iterator<Integer> it = stack.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
