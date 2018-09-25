package sort;

import static queue.MaxPQ.sink;
import static sort.Example.exch;

@SuppressWarnings("unused")
public class Heap {
    /**
     * 堆排序
     *
     * @param a array
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N / 2; k >= 1; k--)
            sink(a, k, N);
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }
}
