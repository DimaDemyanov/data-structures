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
public class CheckIfSimple {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            Scanner scanner = new Scanner(reader);
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            int n = scanner.nextInt();
            boolean isSimple = true;
            for (int i = 2; i < (int)Math.sqrt(n) + 1; i++) {
                if(n % i == 0)
                    isSimple = false;
            }
            if(isSimple) writer.write("True");
            else writer.write("False");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
