package map;

import java.util.Stack;

@SuppressWarnings("unused")
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    /**
     * 广度优先搜索
     *
     * @param G Graph
     * @param s int
     */
    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    /**
     * 递归调用广度优先搜索
     *
     * @param G Graph
     * @param s int
     */
    private void bfs(Graph G, int s) {
        Stack<Integer> queue = new Stack<>();
        marked[s] = true;
        queue.push(s);
        while (!queue.isEmpty()) {
            int v = queue.peek();
            for (int w : G.adj(v))
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.push(w);
                }
        }
    }

    private boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * 遍历整棵树
     *
     * @param v int
     * @return Integer
     */
    @SuppressWarnings("Duplicates")
    private Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
}
