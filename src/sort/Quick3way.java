package sort;

@SuppressWarnings("unused")
public class Quick3way extends Example {
    /**
     * 三向切分快速排序
     *
     * @param a  arr
     * @param lo min
     * @param hi max
     */
    @SuppressWarnings("unchecked")
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo;
        int i = lo + 1;
        int gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
}
