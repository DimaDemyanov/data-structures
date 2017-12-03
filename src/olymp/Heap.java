package olymp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Dmitriy on 10/16/2017.
 */
public class Heap {

    public static void evaluateAndAnswer(ArrayPriorityQueue<Integer> queue, String[] args, Writer writer) {
        try {
            if (args[0].equals("1")) {
                //queue.enqueue((Integer.parseInt(args[1])));
                writer.write(new Integer(queue.extractMin()).toString());
                writer.write('\n');
            }
            if (args[0].equals("0")) {
                queue.add(Integer.parseInt(args[1]));
            }
        } catch (Exception ex) {
        }
    }

    public static void main(String[] args) {
        try {
            ArrayPriorityQueue<Integer> queue = new ArrayPriorityQueue<>();
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            Scanner scanner = new Scanner(reader);
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            int n = scanner.nextInt();
            String[] strs;
            if(scanner.hasNextLine())
                scanner.nextLine();
            //for (int i = 0; i < n; i++) {
            while(scanner.hasNextLine()) {
                strs = scanner.nextLine().split(" ");
                evaluateAndAnswer(queue, strs, writer);
                // if (strs[0].equals("exit")) break;
                //}
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public interface IPriorityQueue<Key extends Comparable<Key>> extends Iterable<Key> {

        void add(Key key);

        Key peek();

        Key extractMin();

        boolean isEmpty();

        int size();
    }


    public static class ArrayPriorityQueue<Key extends Comparable<Key>> implements IPriorityQueue<Key> {

        private Key[] elementData;
        private Comparator<Key> comparator;
        private int size;
        private final int DEFAULT_CAPACITY = 10;
        private int capacity;

        public ArrayPriorityQueue() {
            size = 0;
            elementData = (Key[]) new Comparable[DEFAULT_CAPACITY];
            capacity = DEFAULT_CAPACITY;
            this.comparator = new QueueComparator();
        }

        public ArrayPriorityQueue(Comparator<Key> comparator) {
            this();
            this.comparator = comparator;
        }

        @Override
        public void add(Key key) {
            size++;
            if (size > capacity) grow();
            elementData[size - 1] = key;
            siftUp();
        }

        @Override
        public Key peek() {
            return elementData[0];
        }

        @Override
        public Key extractMin() {
            swap(0, size - 1);
            Key ret = elementData[size - 1];
            size--;
            //if (size < capacity / 4) shrink();
            siftDown();
            return ret;
        }

        @Override
        public boolean isEmpty() {
                 if (size == 0) return true;
            return false;
        }

        @Override
        public int size() {
              return size;
        }

        private void siftUp() {
            int i = size - 1;
            while (i > 0 && comparator.compare(elementData[(i - 1) / 2], elementData[i]) < 0) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        private void swap(int i, int j) {
            Key tmp = elementData[i];
            elementData[i] = elementData[j];
            elementData[j] = tmp;
        }

        private void siftDown() {
            int i = 0;
            while (2 * i + 1 < size) {
                if (2 * i + 2 < size) {
                    if (comparator.compare(elementData[2 * i + 2], elementData[i]) > 0 &&
                            comparator.compare(elementData[2 * i + 2], (elementData[2 * i + 1])) > 0) {
                        swap(i, 2 * i + 2);
                        i = 2 * i + 2;
                        continue;
                    }
                }
                if (comparator.compare(elementData[2 * i + 1], (elementData[i])) > 0) {
                    swap(i, 2 * i + 1);
                    i = 2 * i + 1;
                    continue;
                }
                return;
            }
        }

        private void grow() {

            capacity = capacity * 3 / 2;
            //Key [] newElementData = (Key[])new Object[capacity];
            elementData = Arrays.copyOf(elementData, capacity);
        }

        private void shrink() {
            capacity = capacity / 4;
            elementData = Arrays.copyOf(elementData, capacity);
        }

        private boolean greater(int i, int j) {
            return comparator == null
                    ? elementData[i].compareTo(elementData[j]) > 0
                    : comparator.compare(elementData[i], elementData[j]) > 0
                    ;
        }

        @Override
        public Iterator<Key> iterator() {

            return null;
        }

        class QueueComparator implements Comparator {

            @Override
            public int compare(Object o1, Object o2) {
                return ((Key) o1).compareTo((Key) o2);
            }
        }

    }
}
