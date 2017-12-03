package olymp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

/**
 * Created by Dmitriy on 10/16/2017.
 */
public class SimpleQueue {

    public static void evaluateAndAnswer(LinkedQueue<Integer> queue, String [] args, Writer writer){
        try {
            if (args[0].equals("push")) {
                queue.enqueue((Integer.parseInt(args[1])));
                writer.write("ok");
            }
            if (args[0].equals("pop")) {
                writer.write(queue.dequeue().toString());
            }
            if (args[0].equals("clear")) {
                queue.clear();
                writer.write("ok");
            }
            if (args[0].equals("size")) {
                writer.write(new Integer(queue.size()).toString());
            }
            if (args[0].equals("front")) {
                writer.write(new Integer(queue.front()).toString());
            }
            if (args[0].equals("exit")) {
                writer.write("bye");
            }
            writer.write('\n');
        }catch (Exception ex){
        }
    }

    public static void main(String[] args) {
        try {
            LinkedQueue<Integer> queue = new LinkedQueue<>();
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            Scanner scanner = new Scanner(reader);
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            String [] strs;
            while(scanner.hasNextLine()){
                strs = scanner.nextLine().split(" ");
                evaluateAndAnswer(queue, strs, writer);
                if(strs[0].equals("exit")) break;
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class LinkedQueue<Item> implements IQueue<Item> {

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

        public Item front() {
        /* TODO: implement it */
            Item ret = tail.item;
            return ret;
        }

        public void clear(){
            head = null;
            tail = null;
            size = 0;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public int size() {
            return size;
        }

        private class Node<Item> {
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

    }

    public interface IQueue<Item> {

        void enqueue(Item item);

        Item dequeue();

        boolean isEmpty();

        int size();

    }


}
