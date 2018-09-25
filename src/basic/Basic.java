package basic;

import java.util.stream.IntStream;

@SuppressWarnings("ALL")
public class Basic {

    /**
     * 递归求解非负整数p和q的最大公约数
     *
     * @param p 非负整数
     * @param q 非负整数
     * @return p和q的最大公约数为q和r的最大公约数
     */
    private static int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    /**
     * 二分查找
     *
     * @param key 查找值
     * @param a   目标数组
     * @return 值下标, 未找到返回-1
     */
    private static int BinarySearch(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    /**
     * 计算整数绝对值
     *
     * @param x 待解整数
     * @return x的绝对值
     */
    private static int abs(int x) {
        if (x < 0) return -x;
        else return x;
    }

    /**
     * 判断素数(质数)
     *
     * @param N 整数
     * @return T or F
     */
    private static boolean isPrime(int N) {
        if (N < 2) return false;
        for (int i = 2; i * i <= N; i++)
            if (N % i == 0) return false;
        return true;
    }

    /**
     * 计算平方根(牛顿迭代法)
     *
     * @param c 整数
     * @return sqrt(c)
     */
    private static double sqrt(double c) {
        if (c < 0) return Double.NaN;
        double err = 1e-15;
        double t = c;
        while (Math.abs(t - c / t) > err * t)
            t = (c / t + t) / 2.0;
        return t;
    }

    /**
     * 勾股定理
     *
     * @param a 直角边a
     * @param b 直角边b
     * @return 斜边
     */
    private static double hypotenuse(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }

    /**
     * 计算调和级数
     *
     * @param N 整数
     * @return 调和级数
     */
    private static double H(int N) {
        double sum = IntStream.range(0, N).mapToDouble(i -> 1.0 / i).sum();
        return sum;
    }

    /**
     * 递归实现二分查找
     *
     * @param key 查找值
     * @param a   目标数组
     * @return 递归查找
     */
    private static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1);
    }

    /**
     * @param key 查找值
     * @param a   目标数组
     * @param lo  当前查找范围最小下标
     * @param hi  当前查找范围最大下标
     * @return 值下标, 未找到返回-1
     */
    private static int rank(int key, int[] a, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return rank(key, a, lo, mid - 1);
        else if (key > a[mid]) return rank(key, a, mid + 1, hi);
        else return mid;
    }

    /**
     * 判断字符串是否回文
     *
     * @param s 字符串
     * @return {@code true} 是回文  {@code false} 不是回文
     */
    private static boolean isPalindrome(String s) {
        int N = s.length();
        return IntStream.range(0, N / 2).noneMatch(i -> s.charAt(i) != s.charAt(N - 1 - i));
    }

    /**
     * 判断字符串是否按照字母表顺序排列
     *
     * @param a 字符串
     * @return {@code true} 是 {@code false} 不是
     */
    private static boolean isSorted(String[] a) {
        return IntStream.range(1, a.length).noneMatch(i -> a[i - 1].compareTo(a[i]) > 0);
    }
}
