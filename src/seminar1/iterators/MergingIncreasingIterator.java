package seminar1.iterators;

// DONE !! NOT CHECKED!!!

import java.util.Iterator;

/**
 * Итератор возвращающий последовательность из двух возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 *
 * Time = O(k),
 *  k — суммарное количество элементов
 */
public class MergingIncreasingIterator implements Iterator<Integer> {

    private IncreasingIterator first;
    private IncreasingIterator second;
    private Integer firstNext;
    private Integer secondNext;

    public MergingIncreasingIterator(IncreasingIterator first, IncreasingIterator second) {
        this.first = first;
        this.second = second;
        /* TODO: implement it */
        if(first.hasNext())
            firstNext = first.next();
        if(second.hasNext())
            secondNext = second.next();

    }

    @Override
    public boolean hasNext() {
        if(firstNext != null || secondNext != null)
            return true;
        return false;
    }

    @Override
    public Integer next() {

        Integer next = null;
        if(firstNext != null && secondNext != null){
            if(firstNext < secondNext){
                next = firstNext;
                if(first.hasNext())
                    firstNext = first.next();
            }else{
                next = secondNext;
                if(second.hasNext())
                    secondNext = second.next();
            }
            return next;
        }
        if(firstNext != null){
            next = firstNext;
            if(first.hasNext())
                firstNext = first.next();
        }
        if(secondNext != null) {
            next = secondNext;
            if (second.hasNext())
                secondNext = second.next();
        }
        return next;
    }
}
