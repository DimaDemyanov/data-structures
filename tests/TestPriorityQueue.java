import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import seminar1.collections.ArrayPriorityQueue;
import seminar1.collections.IPriorityQueue;

/**
 * Класс тестирующий интерфейс IPriorityQueue<Integer> на основе ArrayPriorityQueue
 */
public class TestPriorityQueue {

    private IPriorityQueue<Integer> [] queue = new IPriorityQueue[2];
    private final int SMALL_CNT = 10, BIG_CNT = 1000000;

    @Before
    public void init() {
        queue[0] = new ArrayPriorityQueue<>();
        queue[1] = new ArrayPriorityQueue<>(Integer::compareTo);
    }

    @Test
    public void testIsEmpty() {
        for (int i = 0; i < 2; i++) {
            isEmpty(i);
        }
    }

    public void isEmpty(int i) {
        Assert.assertTrue(queue[i].isEmpty());
        Assert.assertEquals(queue[i].size(), 0);
    }

    @Test
    public void testIsNotEmpty() {
        for (int i = 0; i < 2; i++) {
            isNotEmpty(i);
        }
    }

    public void isNotEmpty(int i) {
        queue[i].add(0);
        Assert.assertFalse(queue[i].isEmpty());
        Assert.assertEquals(queue[i].size(), 1);
    }

    @Test
    public void testNullAddition(){
        for (int i = 0; i < 2; i++) {
            tryNullAddition(i);
        }
    }

    public void tryNullAddition(int i){
        try{
            queue[i].add(null);
            Assert.fail();
        } catch (NullPointerException ex){

        }
    }

    @Test
    public void testAddition() {
        for (int i = 0; i < 2; i++) {
            tryAddition(i, SMALL_CNT);
        }
    }

    @Test
    public void testAdditionBig() {
        for (int i = 0; i < 2; i++) {
            tryAddition(i, BIG_CNT);
        }
    }

    public void tryAddition(int i, int n) {
        int testArray[] = new int[n];
        for (int j = 0; j < n; j++) {
            testArray[j] = j;
        }
        for (int j = 0; j < testArray.length; j++) {
            queue[i].add(testArray[j]);
        }
        for (int j = 0; j < testArray.length; j++) {
            Assert.assertEquals((int)queue[i].extractMin(), testArray[j]);
        }
    }

    @Test
    public void testExtractMinAndPeek(){
        for (int i = 0; i < 2; i++) {
            tryExtractMin(i, SMALL_CNT);
        }
    }

    @Test
    public void testExtractMinAndPeekBig(){
        for (int i = 0; i < 2; i++) {
            tryExtractMin(i, BIG_CNT);
        }
    }

    public void tryExtractMin(int i, int n) {
        int testArray[] = new int[n];
        Random random = new Random();
        for (int j = 0; j < n; j++){
            testArray[j] = random.nextInt();
        }
        for (int j = 0; j < testArray.length; j++) {
            queue[i].add(testArray[j]);
        }
        Arrays.sort(testArray);
        for (int j = 0; j < testArray.length; j++) {
            Assert.assertEquals((int)queue[i].peek(), testArray[j]);
            Assert.assertEquals((int)queue[i].extractMin(), testArray[j]);
        }
    }

    @Test
    public void testSize(){
        for (int i = 0; i < 2; i++) {
            checkSize(i, SMALL_CNT);
        }
    }

    @Test
    public void testSizeBig(){
        for (int i = 0; i < 2; i++) {
            checkSize(i, BIG_CNT);
        }
    }

    public void checkSize(int i, int n){
        for (int j = 0; j < n; j++) {
            Assert.assertEquals(queue[i].size(), j);
            queue[i].add(0);
        }
        for (int j = 0; j < n; j++) {
            Assert.assertEquals(queue[i].size(), n - j);
            queue[i].extractMin();
        }
    }

    @Test
    public void testIterator(){
        for (int i = 0; i < 2; i++) {
            tryIterator(i, 100);
        }
    }

    public void tryIterator(int i, int n) {
        int testArray[] = new int[n];
        for (int j = 0; j < n; j++) {
            testArray[j] = j;
        }
        Iterator<Integer> it = queue[i].iterator();
    }

    @Test
    public void testExtractAndPeekEmpty() {
        for (int i = 0; i < 2; i++) {
            tryExtractAndPeekEmpty(i);
        }
    }

    public void tryExtractAndPeekEmpty(int i) {
        try{
            queue[i].extractMin();
            queue[i].peek();
            Assert.fail();
        } catch (NoSuchElementException ex){

        }
    }
}
