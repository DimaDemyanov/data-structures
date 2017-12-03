package olymp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//import seminar1.collections.IStack;
//import seminar1.collections.LinkedStack;

/**
 * Created by Dmitriy on 10/16/2017.
 */
public class Postfix {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            Scanner scanner = new Scanner(reader);
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            LinkedStack<Integer> stack = new LinkedStack<>();
            char c;
//            int first = 0, second = 0;
//            boolean isFirst = false;
//            boolean isSecond = false;
            while(scanner.hasNext()){
                if(scanner.hasNextInt()){
                    stack.push(scanner.nextInt());
                    continue;
                }
                c = scanner.next().charAt(0);
                if (c == '+'){
                    stack.push(stack.pop() + stack.pop());
                }
                if (c == '-'){
                    stack.push(- stack.pop() + stack.pop());
                }
                if (c == '*'){
                    stack.push(stack.pop() * stack.pop());
                }
            }
            writer.write(new Integer(stack.pop()).toString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface IStack<Item> /*extends Iterable<Item>*/ {

        void push(Item item);

        Item pop();

        boolean isEmpty();

        int size();
    }


    public static class LinkedStack<Item> implements IStack<Item> {

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
            size = size + (size > 0 ? 1 : 0);
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



        private class Node<Item> {
            Item item;
            Node<Item> next;

            public Node(Item item, Node<Item> next) {
                this.item = item;
                this.next = next;
            }
        }

    }

}
