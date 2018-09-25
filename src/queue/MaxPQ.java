package queue;

@SuppressWarnings("unused")
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    /**
     * 创建一个最大容量为maxN的优先队列
     * 优先队列是一个基于堆的完全二叉树
     *
     * @param maxN max_length
     */
    @SuppressWarnings("unchecked")
    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    /**
     * 返回队列是否为空
     *
     * @return {@code true}
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 返回优先队列中的元素个数
     *
     * @return pq_size
     */
    public int size() {
        return N;
    }

    /**
     * 向优先队列中插入元素
     *
     * @param v key
     */
    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    /**
     * 删除并返回最大元素
     *
     * @return pq_max
     */
    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    /**
     * 堆元素比较值大小
     *
     * @param i var_i
     * @param j var_j
     * @return {@code true}
     */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * 堆元素交换
     *
     * @param i var_i
     * @param j var_j
     */
    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    /**
     * 由下至上的堆有序化(上浮)
     *
     * @param k node
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 由上至下的堆有序化(下沉)
     *
     * @param k node
     */
    @SuppressWarnings("SameParameterValue")
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public static void sink(Comparable[] a, int k, int N) {
//        while (2 * k <= N) {
//            int j = 2 * k;
//            if (j < N && less(j, j + 1)) j++;
//            if (!less(k, j)) break;
//            exch(k, j);
//            k = j;
//        }
//    }
    }
}
