package olymp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Dmitriy on 10/16/2017.
 */
public class Columns {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            Scanner scanner = new Scanner(reader);
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            int x = scanner.nextInt();
            int n = scanner.nextInt();
            boolean[] isGood = new boolean[n];
            Arrays.fill(isGood, false);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int a = scanner.nextInt();
                    if(a == x)
                        isGood[j] = true;
                }
            }
            for (int i = 0; i < n; i++) {
                if (isGood[i]){
                    writer.write("YES\n");
                }else{
                    writer.write("NO\n");
                }
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
