package olymp;

import java.util.*;

public class Ropes {

    public static int getRopesCnt(MyArrayList<Integer> list, long length){
        int cnt = 0;
        for (int i = 0; i < list.size(); i++) {
            cnt += list.get(i)/length;
        }
        return cnt;
    }

    public static long binSearch(MyArrayList<Integer> list, long sum, int n, int k){
        long b = 0;
        long e = sum;
        long newBound;
        while(e - b > 1) {
            newBound = (b + e) / 2 ;
//            if (list.get(newBound) == el)
//                return true;
            if (getRopesCnt(list, newBound) >= k)
                b = newBound;
            else
                e = newBound;
        }

        if(getRopesCnt(list, e) >= k){
            return e;
        }
        return b;
    }

    public static void main(String[] args) {
        int n, k;
        long sum = 0;
        MyArrayList<Integer> list = new MyArrayList<>();
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        k = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int s = scan.nextInt();
            list.add(s);
            sum += s;
        }
        System.out.println(binSearch(list,sum,n,k));
    }

    public static class MyArrayList<T> {
        private final int INIT_SIZE = 16;
        private final int CUT_RATE = 4;
        private Object[] array = new Object[INIT_SIZE];
        private int pointer = 0;


        public void add(T item) {
            if(pointer == array.length-1)
                resize(array.length*2); 
            array[pointer++] = item;
        }


        public T get(int index) {
            return (T) array[index];
        }


        public void remove(int index) {
            for (int i = index; i<pointer; i++)
                array[i] = array[i+1];
            array[pointer] = null;
            pointer--;
            if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE)
                resize(array.length/2);
        }

        public int size() {
            return pointer;
        }


        private void resize(int newLength) {
            Object[] newArray = new Object[newLength];
            System.arraycopy(array, 0, newArray, 0, pointer);
            array = newArray;
        }
    }
}