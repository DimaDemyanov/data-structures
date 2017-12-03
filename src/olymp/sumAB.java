package olymp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Created by Dmitriy on 10/16/2017.
 */
public class sumAB {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            Scanner scanner = new Scanner(reader);
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            int n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                int a, b;
                a = scanner.nextInt();
                b = scanner.nextInt();
                writer.write((new Integer(a + b)).toString() + '\n');
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
