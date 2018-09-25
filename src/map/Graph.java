package map;

import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Graph {
    private final int V;    // 顶点数目
    private int E;  //边的数目
    private ArrayList<Integer>[] adj; //邻接表

    /**
     * 创建一个含义V个顶点但不含有边的图
     *
     * @param V int
     */
    @SuppressWarnings("unchecked")
    private Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        for (int v = 0; v < V; v++)
            adj[v] = new ArrayList<>();
    }

    public Graph(Scanner in) {
        this(in.nextInt());
        int E = in.nextInt();
        for (int i = 0; i < E; i++) {
            int v = in.nextInt();
            int w = in.nextInt();
            addEdge(v, w);
        }
    }

    /**
     * 顶点数
     *
     * @return int
     */
    int V() {
        return V;
    }

    /**
     * 边数
     *
     * @return int
     */
    private int E() {
        return E;
    }

    /**
     * 向图中添加一条边
     *
     * @param v int
     * @param w int
     */
    private void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    /**
     * 和v相邻的所有顶点
     *
     * @param v int
     * @return Integer
     */
    Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 计算v的度数
     *
     * @param G Graph
     * @param v int
     * @return int
     */
    @SuppressWarnings("unused")
    private static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v)) degree++;
        return degree;
    }

    /**
     * 计算所有定点的最大度数
     *
     * @param G Graph
     * @return int
     */
    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++)
            if (degree(G, v) > max)
                max = degree(G, v);
        return max;
    }

    /**
     * 计算所有定点的平均度数
     *
     * @param G Graph
     * @return double
     */
    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    public static double avgDegree(Graph G) {
        return 2 * G.E() / G.V();
    }

    /**
     * 计算自环的个数
     *
     * @param G Graph
     * @return int
     */
    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v))
                if (v == w) count++;
        return count / 2;
    }

    /**
     * 图的邻接表的字符串表示
     *
     * @return String
     */
    @SuppressWarnings("StringConcatenationInLoop")
    @Override
    public String toString() {
        String s = V + "vertices, " + E + "edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (int w : this.adj(v))
                s += w + " ";
            s += "\n";
        }
        return s;
    }
}
