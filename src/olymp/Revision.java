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
public class Revision {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            Scanner scanner = new Scanner(reader);
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            int n = scanner.nextInt();
            int min, min_prev;
            min = scanner.nextInt();
            min_prev = scanner.nextInt();

            if(min_prev < min){
                int tmp = min_prev;
                min_prev = min;
                min = tmp;
            }

            for (int i = 2; i < n; i++) {
                int a = scanner.nextInt();
                if(a <= min){
                    min_prev = min;
                    min = a;
                }
                if(a > min && a < min_prev){
                    min_prev = a;
                }
            }
            writer.write(new Integer(min).toString() + " " + new Integer(min_prev).toString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
