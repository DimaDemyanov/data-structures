package olymp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Dmitriy on 10/16/2017.
 */
public class MegaNOD {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            Scanner scanner = new Scanner(reader);
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            int[] a = new int[1000];
            boolean isAns = false;
            int i = 0;
            while(scanner.hasNextInt())
                a[i++] = scanner.nextInt();
            int min = a[0];
            while(isAns == false){
                isAns = true;
                int minNext = a[0];
                for (int j = 0; j < i; j++){
                    if(a[j] != min) isAns = false;
                    a[j] = a[j] % min;
                    if(a[j] == 0) a[j] += min;
                    if(a[j] < minNext) minNext = a[j];
                }
                min = minNext;
            }
            writer.write(new Integer(min).toString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}