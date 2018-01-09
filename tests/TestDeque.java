import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import seminar1.collections.CyclicArrayDeque;
import seminar1.collections.IDeque;
import seminar1.collections.LinkedDeque;

/**
 * Класс тестирующий интерфейс {@link IDeque<Integer>} в двух реализациях:
 * 1) на массиве {@link CyclicArrayDeque<Integer>}
 * 2) на списке {@link LinkedDeque<Integer>}
 */
@RunWith(value = Parameterized.class)
public class TestDeque {

    @Parameterized.Parameter()
    public Class<?> testClass;

    private IDeque<Integer> deque;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Class<?>> data() {
        return Arrays.asList(
                CyclicArrayDeque.class,
                LinkedDeque.class
        );
    }

    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        try {
            deque = (IDeque<Integer>) testClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isEmpty() {
        Assert.assertTrue(deque.isEmpty());
        Assert.assertEquals(deque.size(), 0);
    }

    @Test
    public void testPushFront1 (){
        int testArray[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i < testArray.length; i++) {
            deque.pushFront(testArray[i]);
        }
        for (int i = 0; i < testArray.length; i++) {
            Assert.assertTrue(deque.popBack() == testArray[i]);
        }
    }

    @Test
    public void testPushFront2 (){
        int testArray[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i < testArray.length; i++) {
            deque.pushFront(testArray[i]);
        }
        for (int i = testArray.length - 1; i >= 0; i--) {
            Assert.assertTrue(deque.popFront() == testArray[i]);
        }
    }

    @Test
    public void testPushBack1 (){
        int testArray[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i < testArray.length; i++) {
            deque.pushBack(testArray[i]);
        }
        for (int i = 0; i < testArray.length; i++) {
            Assert.assertTrue(deque.popFront() == testArray[i]);
        }
    }

    @Test
    public void testPushBack2 (){
        int testArray[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i < testArray.length; i++) {
            deque.pushBack(testArray[i]);
        }
        for (int i = testArray.length - 1; i >= 0; i--) {
            Assert.assertTrue(deque.popBack() == testArray[i]);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testPushNull(){
        deque.pushBack(null);
        deque.pushFront(null);
        Assert.fail();
    }

    @Test(expected = NoSuchElementException.class)
    public void testPopBackEmpty (){
        deque.popBack();
        Assert.fail();
    }

    @Test(expected = NoSuchElementException.class)
    public void testPopFrontEmpty (){
        deque.popFront();
        Assert.fail();
    }

    @Test
    public void testSize (){
        int n = 100;
        Assert.assertTrue(deque.size() == 0);
        for(int i = 0; i < n; i++)
            deque.pushBack(1);
        Assert.assertTrue(deque.size() == n);
    }

    @Test
    public void testIterator(){
        int n = 100;
        int testArray[] = new int[n];
        for (int i = 0; i < 100; i++) {
            testArray[i] = i;
            deque.pushBack(testArray[i]);
        }
        Iterator<Integer> it = deque.iterator();
        int i = 0;
        while (it.hasNext()){
            Assert.assertTrue(it.next() == testArray[i++]);
        }
    }

}
