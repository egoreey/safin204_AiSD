package hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int a, b, n;
        try {
            Scanner scanner = new Scanner(new File("graph.txt"));
            n = Integer.parseInt(scanner.nextLine());
            int[] graph = new int[n];
            for (int i = 0; i < n; i++) {
                graph[i] = i;
            }

            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);

                boolean flag = false;
                if (a > b) {
                    int tmp = a;
                    a = b;
                    b = tmp;
                    flag = true;
                }

                if (graph[a] != graph[b]) {
                    if (flag) System.out.println("(" + b + ", " + a + ")");
                    else System.out.println("(" + a + ", " + b + ")");

                    int graphElem = graph[b];
                    for (int i = 0; i < n; i++) {
                        if (graph[i] == graphElem) graph[i] = graph[a];
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
