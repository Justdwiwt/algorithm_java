package sort;

@SuppressWarnings("ALL")
public class Insertion extends Example {
    /**
     * 插入排序
     * 对于0到N-1之间的每一个i，将a[i]与a[0]到a[i-1]中比它小的所有元素依次有序交换
     * 索引i到达数组右端时排序完成
     *
     * @param a array
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++)
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }
}
