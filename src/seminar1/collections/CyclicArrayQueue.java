package seminar1.collections;

import java.util.Arrays;
import java.util.Iterator;

public class CyclicArrayQueue<Item> implements IQueue<Item> {

    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private Item[] elementData;
    private int size;
    private int begin, end;

    public CyclicArrayQueue(){
        elementData = (Item [])new Object[DEFAULT_CAPACITY ];
        capacity = DEFAULT_CAPACITY;
        size = 0;
        begin = 0;
        end = -1;
    }

    @Override
    public void enqueue(Item item) {
        /* TODO: implement it */
        if(item != null) {
            end = (end + 1) % capacity;
            size++;
            grow();
            elementData[end] = item;
        }
    }

    @Override
    public Item dequeue() {
        /* TODO: implement it */
        Item res = null;
        if(elementData[begin] != null){
            res = elementData[begin];
            size--;
            begin = (begin + 1) % capacity;
            shrink();
        }

        return res;
    }

    @Override
    public boolean isEmpty() {
        return size > 0 ? true : false;
    }

    @Override
    public int size() {
        /* TODO: implement it */

        return size;
    }

    private void grow() {
        /**
         * TODO: implement it
         * Если массив заполнился,
         * то увеличить его размер в полтора раз
         */
         if(end == begin && size > 1) {
            int newCapacity = capacity * 3 / 2;
            Item [] newElementData = (Item[]) new Object[newCapacity];
            for (int i = begin; i < capacity; i++) {
                newElementData[i - begin] = elementData[i];
            }
            for (int i = 0; i < end; i++) {
                newElementData[i + capacity - begin] = elementData[i];
            }
            elementData = newElementData;
            end = end + capacity - begin;
             begin = 0;
             capacity = newCapacity;
         }
    }

    private void shrink() {
        /**
         * TODO: implement it
         * Если количество элементов в четыре раза меньше,
         * то уменьшить его размер в два раза
         */
        if(capacity / 4 > size && size > 0) {
            //capacity = capacity / 4;
            if(end < begin){
                int newCapacity = capacity / 4;
                Item [] newElementData = (Item[]) new Object[newCapacity];
                for (int i = begin; i < capacity; i++) {
                    newElementData[i - begin] = elementData[i];
                }
                for (int i = 0; i < end; i++) {
                    newElementData[i + capacity - begin] = elementData[i];
                }
                end = end + capacity - begin;
                begin = 0;
                capacity = newCapacity;
            }else{
                int newCapacity = capacity / 4;
                Item [] newElementData = (Item[]) new Object[newCapacity];
                for (int i = begin; i <= end ; i++) {
                    newElementData[i - begin] = elementData[i];
                }
                elementData = newElementData;
                capacity = newCapacity;
                end = end - begin;
                begin = 0;
            }
        }
    }

    @Override
    public Iterator<Item> iterator() {
        /* TODO: implement it */
        Iterator<Item> iter = new Iterator<Item>() {
           // Item [] elements = elementData;
            int cap = capacity;
//            int b = begin;
//            int e = end;
            int s = size;
            int curr = begin;
            boolean hasNext = true;

            @Override
            public boolean hasNext() {
                return size > 0 ? hasNext : false;
            }

            @Override
            public Item next() {
                Item res = null;
                if(hasNext()){
                    if(curr == end - 1 && s != 0)
                        hasNext = false;
                    curr = (curr + 1) % cap;
                    res = elementData[curr];
                }
                return res;
            }
        };
        return iter;
    }

    public static void main(String[] args) {
        CyclicArrayQueue<Integer> queue = new CyclicArrayQueue<>();
//        for (int j = 0; j < 10; j++) {
//            for (int i = 0; i < 100; i++) {
//                queue.enqueue(i + j * 10);
//            }
//            for (int i = 0; i < 5; i++) {
//                System.out.println(queue.dequeue().toString() + ' ');
//            }
//        }
        for (int i = 0; i < 100; i++) {
            queue.enqueue(i);
        }
//        for (int i = 0; i < 74; i++) {
//            System.out.println(queue.dequeue().toString() + ' ');
//        }
        for (int i = 0; i < 15; i++) {
            queue.dequeue();
        }
        Iterator it = queue.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

}
