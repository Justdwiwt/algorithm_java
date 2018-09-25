package sort;

@SuppressWarnings("ALL")
public class Shell extends Example {
    /**
     * 希尔排序
     * 交换不相邻的元素以对数组的局部排序
     * 最终用插入排序将有序的数组排序
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3)
            h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++)
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            h = h / 3;
        }
    }
}
