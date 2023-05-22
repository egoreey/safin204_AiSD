package hw3;

public class Graph {
    private final int[] parents;

    public Graph(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    public int find(int x) {
        if (parents[x] != x) parents[x] = find(parents[x]);
        return parents[x];
    }

    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) parents[rootA] = rootB;
    }
}
