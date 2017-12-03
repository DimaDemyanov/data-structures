package seminar1.collections;

import java.util.Iterator;

public class CyclicArrayDeque<Item> implements IDeque<Item> {

    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private Item[] elementData;
    private int size;
    private int begin, end;

    public CyclicArrayDeque() {
        elementData = (Item [])new Object[DEFAULT_CAPACITY ];
        capacity = DEFAULT_CAPACITY;
        size = 0;
        begin = 0;
        end = -1;
    }

    @Override
    public void pushFront(Item item) {
        if(item != null) {
            begin = (begin - 1 + capacity) % capacity;
            size++;
            grow();
            elementData[begin] = item;
        }
    }

    @Override
    public void pushBack(Item item) {
        if(item != null) {
            end = (end + 1) % capacity;
            size++;
            grow();
            elementData[end] = item;
        }
    }

    @Override
    public Item popFront() {
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
    public Item popBack() {
        Item res = null;
        if(elementData[end] != null){
            res = elementData[end];
            size--;
            end = (end - 1 + capacity) % capacity;
            shrink();
        }

        return res;
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

    private void grow() {
        if((end + capacity) % capacity == begin && size > 1) {
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
        Iterator<Item> iter = new Iterator<Item>() {
            // Item [] elements = elementData;
            int cap = capacity;
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
                    curr = (curr + 1 + cap) % cap;
                    res = elementData[curr];
                }
                return res;
            }
        };
        return iter;
    }

    public static void main(String[] args) {
        CyclicArrayDeque<Integer> queue = new CyclicArrayDeque<>();

        for (int i = 0; i < 100; i++) {
            queue.pushFront(i);
        }
        for (int i = 0; i < 15; i++) {
            queue.popBack();
        }
        Iterator it = queue.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

}
