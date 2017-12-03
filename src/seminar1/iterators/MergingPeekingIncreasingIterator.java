package seminar1.iterators;

import java.util.Comparator;
import java.util.Iterator;

import olymp.Heap;
import seminar1.collections.ArrayPriorityQueue;

/**
 * Итератор возвращающий последовательность из N возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 *
 * Time = O(n + k * log n),
 *  n — количество итераторов
 *  k — суммарное количество элементов
 */
public class MergingPeekingIncreasingIterator implements Iterator<Integer> {

    private Comparator<PeekingIncreasingIterator> comparator = (p1, p2) -> p1.peek().compareTo(p2.peek());

    private ArrayPriorityQueue<HeapElement> queue;

    public MergingPeekingIncreasingIterator(IPeekingIterator... peekingIterator) {
        queue = new ArrayPriorityQueue<>();
        for (int i = 0; i < peekingIterator.length; i++) {
            if (peekingIterator[i].hasNext())
                queue.add(new HeapElement(peekingIterator[i]));
        }
    }

    @Override
    public boolean hasNext() {
        return queue.size() > 0 ? true : false;
    }

    @Override
    public Integer next() {
        if(hasNext()) {
            HeapElement el = queue.extractMin();
            Integer res = el.key;
            if(el.it.hasNext())
                queue.add(new HeapElement(el.it));
            return res;
        }
        return null;
    }

    class HeapElement implements Comparable<HeapElement>{
        Integer key;
        IPeekingIterator<Integer> it;

        public HeapElement(IPeekingIterator<Integer> it) {
            this.it = it;
            if (it.hasNext()) this.key = it.next();
        }


        @Override
        public int compareTo(HeapElement o) {
            return key.compareTo(((HeapElement) o).key);
        }
    }

    public static void main(String[] args) {
        //IPeekingIterator iter = ;
        MergingPeekingIncreasingIterator it =
                new MergingPeekingIncreasingIterator(new PeekingIncreasingIterator(1, 10, 5), new PeekingIncreasingIterator(1, 10, 5), new PeekingIncreasingIterator(1, 10, 5));
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
