package tree;

@SuppressWarnings("unused")
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    /**
     * 二分查找
     * 基于有序数组
     *
     * @param capacity 容量
     */
    @SuppressWarnings("unchecked")
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    /**
     * 长度
     *
     * @return N
     */
    private int size() {
        return N;
    }

    /**
     * 获取指定位置的值
     *
     * @param key key
     * @return value
     */
    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    /**
     * 判空
     *
     * @return {@code true}
     */
    private boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 基于有序数组的二分查找
     * 迭代
     *
     * @param key key
     * @return index
     */
    private int rank(Key key) {
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    /**
     * 查找键
     * 找到更新值
     * 否则创建新的元素
     *
     * @param key key
     * @param val value
     */
    public void put(Key key, Value val) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    /**
     * 删除键
     *
     * @param key key
     */
    public void delete(Key key) {
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key) {
        return null;
    }

//        public Key delete(Key key) {
//        return null;
//    }
//    public Iterable<Key> keys(Key lo, Key hi) {
//        Queue<Key> q = new Queue<Key>();
//        for (int i = rank(lo); i < rank(hi); i++)
//            q.enqueue(keys[i]);
//        if (contains(hi))
//            q.enqueue(keys[rank(hi)]);
//        return q;
//    }
}

