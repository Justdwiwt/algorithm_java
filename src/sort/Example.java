package sort;

import java.util.Arrays;
import java.util.stream.IntStream;

@SuppressWarnings("ALL")
public class Example {

    public static void sort(Comparable[] a) {
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a) {
        Arrays.stream(a).map(comparable -> comparable + " ").forEachOrdered(System.out::print);
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        return IntStream.range(1, a.length).noneMatch(i -> less(a[i], a[i - 1]));
    }
}
