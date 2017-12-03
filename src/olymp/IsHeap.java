package olymp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

/**
 * Created by Dmitriy on 10/16/2017.
 */
public class IsHeap {
    public static void main(String[] args) {
        try {
            //SimpleQueue.LinkedQueue<Integer> queue = new SimpleQueue.LinkedQueue<>();
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            Scanner scanner = new Scanner(reader);
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            int n = scanner.nextInt();
            long[] a = new long[n + 1];
            /*if (n < 1000) {
                while (true) {
                    //a[-1] = 3 / 0;
                    if(a[0] == -100) break;
                }
            }*/
            boolean isHeap = true;
            for (int i = 1; i <= n; i++) {
                a[i] = scanner.nextLong();
            }
            for (int i = 1; i <= n; i++) {
                if(i * 2 <= n && a[i * 2] < a[i])
                    isHeap = false;
                if(i * 2 + 1 <= n && a[i * 2 + 1] < a[i])
                    isHeap = false;
            }
            if(isHeap)writer.write("YES");
            else writer.write("NO");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
