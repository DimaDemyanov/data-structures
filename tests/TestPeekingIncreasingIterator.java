import java.util.IllegalFormatCodePointException;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import seminar1.iterators.PeekingIncreasingIterator;

/**
 * Класс тестирующий {@link seminar1.iterators.PeekingIncreasingIterator}
 */
public class TestPeekingIncreasingIterator {

    PeekingIncreasingIterator iterator;

    @Test(expected = NoSuchElementException.class)
    public void testEmptyIterator() {
        new PeekingIncreasingIterator(10, 10, 0, 1).next();
    }

    @Test
    public void checkNumberOfElements(){
        int n = 10;
        iterator = new PeekingIncreasingIterator(0, n + 1, n, 1);
        for (int i = 0; i < 10; i++) {
            if(!iterator.hasNext()) Assert.fail();
            iterator.next();
        }
        if (iterator.hasNext()) Assert.fail();
    }

    @Test
    public void checkStepSize(){
        int n = 100;
        int stepSize = 10;
        int start = 0;
        int lastNumber;
        iterator = new PeekingIncreasingIterator(start, stepSize * n, n, stepSize);
        lastNumber = start;
        for (int i = 0; i < n; i++) {
            int newNumber = iterator.next();
            if(newNumber - lastNumber > stepSize || newNumber - lastNumber < 0) Assert.fail();
            lastNumber = newNumber;
        }
    }

    @Test
    public void checkPeek() {
        int n = 100;
        int stepSize = 10;
        iterator = new PeekingIncreasingIterator(0, stepSize * n, n, stepSize);
        for (int i = 0; i < n; i++) {
            int newNumber = iterator.peek();
            Assert.assertTrue(newNumber == iterator.next());
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIllegalConstructor1(){
        iterator = new PeekingIncreasingIterator(-1, 1, 1, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIllegalConstructor2(){
        iterator = new PeekingIncreasingIterator(0, 0, 1, 1);
        iterator = new PeekingIncreasingIterator(2, 1, 1, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIllegalConstructor3(){
        iterator = new PeekingIncreasingIterator(1, 2, -1, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIllegalConstructor4(){
        iterator = new PeekingIncreasingIterator(1, 2, 1, 0);
    }
}
