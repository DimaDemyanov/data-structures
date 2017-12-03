package seminar1.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class ArrayPriorityQueue<Key extends Comparable<Key>> implements IPriorityQueue<Key> {

    private Key[] elementData;
    private Comparator<Key> comparator;
    private int size;
    private final int DEFAULT_CAPACITY = 10;
    private int capacity;

    public ArrayPriorityQueue() {
        /* TODO: implement it — O(n) */
        size = 0;
        elementData = (Key [])new Comparable[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
        this.comparator = new QueueComparator();
    }

    public ArrayPriorityQueue(Comparator<Key> comparator) {
        /* TODO: implement it — O(n) */
        this();
        this.comparator = comparator;
    }

    @Override
    public void add(Key key) {
        /* TODO: implement it — O(log n) */
        size++;
        if (size > capacity) grow();
        elementData[size - 1] = key;
        siftUp();
    }

    @Override
    public Key peek() {
        /**
         * TODO: implement it — O(1)
         * Посмотреть на минимальный элемент
         */
        return elementData[0];
    }

    @Override
    public Key extractMin() {
        /**
         * TODO: implement it — O(log n)
         * Достать минимальный элемент
         *  и перестроить кучу
         */
        swap(0, size - 1);
        Key ret = elementData[size - 1];
        size--;
        if(size < capacity / 4) shrink();
        siftDown();
        return ret;
    }

    @Override
    public boolean isEmpty() {
        /* TODO: implement it */
        if (size == 0) return true;
        return false;
    }

    @Override
    public int size() {
        /* TODO: implement it */
        return size;
    }

    private void siftUp() {
        /**
         * TODO: implement it — O(log n)
         * Просеивание вверх —
         *  подъём элемента больше родителей
         */
        int i = size - 1;
        while (i > 0 && comparator.compare(elementData[(i - 1) / 2], elementData[i]) > 0) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void swap(int i, int j){
        Key tmp = elementData[i];
        elementData[i] = elementData[j];
        elementData[j] = tmp;
    }

    private void siftDown() {
        /**
         * TODO: implement it — O(log n)
         * Просеивание вниз
         *  спуск элемента меньше детей
         */
        int i = 0;
        while(2 * i + 1 < size){
            if(2 * i + 2 < size){
                if(     comparator.compare(elementData[2 * i + 2],elementData[i]) < 0 &&
                        comparator.compare(elementData[2 * i + 2],(elementData[2 * i + 1])) < 0 ) {
                    swap(i, 2 * i + 2);
                    i = 2 * i + 2;
                    continue;
                }
            }
            if( comparator.compare(elementData[2 * i + 1],(elementData[i])) < 0 ) {
                swap(i, 2 * i + 1);
                i = 2 * i + 1;
                continue;
            }
            return;
        }
    }

    private void grow() {
        /**
         * TODO: implement it
         * Если массив заполнился,
         * то увеличить его размер в полтора раз
         */
        capacity = capacity * 3 / 2;
        //Key [] newElementData = (Key[])new Object[capacity];
        elementData = Arrays.copyOf(elementData, capacity);
    }

    private void shrink() {
        /**
         * TODO: implement it
         * Если количество элементов в четыре раза меньше,
         * то уменьшить его размер в два раза
         */
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
        /* TODO: implement it */
        return null;
    }

    class QueueComparator implements Comparator{

        @Override
        public int compare(Object o1, Object o2) {
            return ((Key)o1).compareTo((Key)o2);
        }
    }

    public static void main(String[] args) {
        ArrayPriorityQueue<Integer> queue = new ArrayPriorityQueue<Integer>();
        for (int i = 0; i < 10000; i++) {
            queue.add(i);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(queue.extractMin() + "iaaa");
        }
        for (int i = 0; i < 10000; i++) {
            queue.add(i);
        }
        for (int i = 0; i < 10000; i++) {
            System.out.println(queue.extractMin() + "iaaa");
        }
    }
}
