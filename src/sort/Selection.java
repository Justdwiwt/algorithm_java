package sort;

@SuppressWarnings("ALL")
public class Selection extends Example {
    /**
     * 选择排序
     * 首先找到数组中最小的元素，与数组第一个元素交换
     * 在剩余元素中找到最小的元素，与数组第二个元素交换
     * ...
     * 不断在剩余元素中选择最小者
     *
     * @param a array
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++)
                if (less(a[j], a[min]))
                    min = j;
            exch(a, i, min);
        }
    }
}
