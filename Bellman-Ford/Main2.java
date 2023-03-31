import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main2 {
    public static int BellmanFord(int graph[][], int v, int e, int src){
        int count = 0;
        int [] dis = new int[v];
        for (int i = 0; i < v; i++) {
            dis[i] = Integer.MAX_VALUE;
            count++;
        }
        dis[src] = 0;
        for (int i = 0; i < v - 1; i++) {
            for (int j = 0; j < e; j++) {
                if (dis[graph[j][0]] != Integer.MAX_VALUE && dis[graph[j][0]] + graph[j][2] < dis[graph[j][1]]){
                 dis[graph[j][1]] = dis[graph[j][0]] + graph[j][2];
                 count++;
                }
            }
        }
        for (int i = 0; i < e; i++) {
            int x = graph[i][0];
            int y = graph[i][1];
            int weight = graph[i][2];
            if (dis[x] != Integer.MAX_VALUE && dis[x] + weight < dis[y]){
                System.out.println("Graph contains negative weight cycle");
            }
            count++;
        }
        System.out.println("Vertex distance from Source");
        for (int i = 0; i < v; i++) {
            System.out.println(i + "\t\t" + dis[i]);
        }
        return count;
    }
    public static void fileWriter() throws IOException {
        File file = new File("input.txt");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter("input.txt");
        double rem = Math.random()*10000;
        for (int i = 0; i < rem + 50; i++) {
            fileWriter.write((int) (Math.random()*100)
                    + "," + (int) (Math.random()*100)
                    + "," + (int) (Math.random()*Integer.MAX_VALUE - Integer.MAX_VALUE/2));
            double rem1 = Math.random() * 9900 + 99;
            for (int j = 0; j < rem1; j++) {
                fileWriter.write(";" + (int) (Math.random()*100)
                        + "," + (int) (Math.random()*100)
                        + "," + (int) (Math.random()*Integer.MAX_VALUE - Integer.MAX_VALUE/2));
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
    public static void main(String[] args) throws IOException{
        fileWriter();
        Scanner scanner = new Scanner(new File("input.txt"));
        Set<Integer> uniquePeaks = new HashSet<>();
        while(scanner.hasNext()){
            String[] str = scanner.nextLine().split(";");
            int graph[][] = new int[str.length][3];
            for (int i = 0; i < str.length; i++) {
                String[] digits = str[i].split(",");
                for (int j = 0; j < digits.length; j++) {
                    uniquePeaks.add(Integer.parseInt(digits[0]));
                    uniquePeaks.add(Integer.parseInt(digits[1]));
                    graph[i][j] = Integer.parseInt(digits[j]);
                }
            }
            int max = 0;
            for (Integer r:uniquePeaks) {
                 max = r;
            }
            long timeStart = System.nanoTime();
            System.out.println(BellmanFord(graph, max + 1, str.length, 0));
            long timeFinish = System.nanoTime();
            long time = timeFinish - timeStart;
            System.out.println(time);
            uniquePeaks.clear();
        }
    }
}
