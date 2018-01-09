import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.*;

import seminar1.iterators.MergingPeekingIncreasingIterator;
import seminar1.iterators.PeekingIncreasingIterator;

/**
 * Класс тестирующий {@link seminar1.iterators.MergingPeekingIncreasingIterator}
 * на основе {@link seminar1.iterators.IPeekingIterator<Integer>}
 */
public class TestMergingPeekingIncreasingIterator {
    MergingPeekingIncreasingIterator iterator;

    @Test(expected = NullPointerException.class)
    public void testEmpty() {
        iterator = new MergingPeekingIncreasingIterator(null);
    }

    @Test
    public void testMaxCount() {
        int n = 10;
        iterator = new MergingPeekingIncreasingIterator(new PeekingIncreasingIterator(0, n + 1, n, 1),
                                                        new PeekingIncreasingIterator(0, n + 1, n, 1));
        int i = 0;
        while (iterator.hasNext()){
            i++;
            iterator.next();
        }
        Assert.assertEquals(i, 2*n);
    }

    @Test
    public void testElements() {
        int n = 10;
        int stepSize = 5;
        int itCnt = 5;
        int mergingArray[] = new int[itCnt * n];
        int iteratorsArray[] = new int[itCnt * n];
        PeekingIncreasingIterator it[] = new PeekingIncreasingIterator[itCnt];
        for (int i = 0; i < itCnt; i++) {
            it[i] = new PeekingIncreasingIterator(0, (n + 1) * stepSize, n, stepSize);
        }
        iterator = new MergingPeekingIncreasingIterator(it);
        int i = 0;
        while (iterator.hasNext()){
            mergingArray[i] = iterator.next();
            i++;
        }
        iteratorsArray = Arrays.copyOf(mergingArray, mergingArray.length);
        Arrays.sort(iteratorsArray);
        Assert.assertArrayEquals(iteratorsArray, mergingArray);
    }
}
