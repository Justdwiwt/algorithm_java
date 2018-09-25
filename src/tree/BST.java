package tree;

@SuppressWarnings("unused")
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    /**
     * 基于二叉查找树的符号表
     */
    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int N;  //以该结点为根的子树中的结点总数

        Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            N = n;
        }
    }

    /**
     * 返回以该结点为根结点的结点个数
     *
     * @return N
     */
    public int size() {
        return size(root);
    }

    /**
     * 返回以该结点为根结点的结点个数
     *
     * @param x root
     * @return N
     */
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     * 在以x为根节点的子树中查找并返回key所对应的值
     * 如果找不到返回null
     *
     * @param key key
     * @return {@code null}
     * @see #get(Node, Comparable)
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 在以x为根节点的子树中查找并返回key所对应的值
     * 如果找不到返回null
     *
     * @param x   root
     * @param key key
     * @return {@code null}
     */
    @SuppressWarnings("Duplicates")
    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    /**
     * 查找key
     * 找到则更新它的值
     * 否则为它创建一个新结点
     *
     * @param key key
     * @param val value
     * @see #put(Node, Comparable, Object)
     */
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    /**
     * 如果key存在于以x为根节点的子树中则更新它的值
     * 否则将以key和val为键值对的新结点插入到该子树中
     *
     * @param x   root
     * @param key key
     * @param val value
     * @return root
     */
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * 返回以该结点为根结点的最小结点
     *
     * @return min_key
     * @see #min(Node)
     */
    public Key min() {
        return min(root).key;
    }

    /**
     * 返回以该结点为根结点的最小结点
     *
     * @param x root
     * @return min_key
     */
    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    /**
     * 返回以该结点为根结点的最大结点
     *
     * @return max_key
     * @see #max(Node)
     */
    public Key max() {
        return max(root).key;
    }

    /**
     * 返回以该结点为根结点的最大结点
     *
     * @param x root
     * @return max_key
     */
    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    /**
     * 结点上移
     *
     * @param key key
     * @return Node
     * @see #floor(Node, Comparable)
     */
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    /**
     * 结点上移
     *
     * @param x   root
     * @param key key
     * @return Node
     */
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    /**
     * 结点下移
     *
     * @param key key
     * @return Node
     * @see #ceiling(Node, Comparable)
     */
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    /**
     * 结点下移
     *
     * @param x   root
     * @param key key
     * @return Node
     */
    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return floor(x.right, key);
        Node t = floor(x.left, key);
        if (t != null) return t;
        else return x;
    }

    /**
     * 查找值为value的key
     *
     * @param k value
     * @return key
     * @see #select(Node, int)
     */
    public Key select(int k) {
        return select(root, k).key;
    }

    /**
     * 查找值为value的key
     *
     * @param x root
     * @param k value
     * @return key
     */
    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    /**
     * 删除最小结点
     *
     * @see #deleteMin(Node)
     */
    public void deleteMin() {
        root = deleteMin(root);
    }

    /**
     * 删除最小结点
     *
     * @param x root
     * @return Node
     */
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * 删除指定结点
     *
     * @param key key
     * @see #delete(Node, Comparable)
     */
    public void delete(Key key) {
        root = delete(root, key);
    }

    /**
     * 删除指定结点
     *
     * @param x   root
     * @param key key
     * @return Node
     */
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * 遍历以该结点为根结点的所有子结点
     *
     * @param x root
     */
    private void print(Node x) {
        if (x == null) return;
        print(x.left);
        System.out.println(x.key);
        print(x.right);
    }

    /**
     * 二叉树检查
     *
     * @param x root
     * @return {@code true} 以该结点为根的子树中的结点总数和计数器的值N相等
     */
    private boolean isBinaryTree(Node x) {
        return x.N == size(x);
    }

    /**
     * 有序性检查
     *
     * @param x  root
     * @param lo min_value
     * @param hi max_value
     * @return {@code true} 以该结点为根的子树中的所有结点都在min和max之间,
     * min和max的确分布是数中的最小和最大的结点,
     * 且二叉查找树的有序性对树中的所有键都成立
     */
    private boolean isOrdered(Node x, Key lo, Key hi) {
        return false;
    }

    /**
     * 等值键检查
     *
     * @param x root
     * @return {@code true} 以该结点为根的二叉查找树中不含有等值的键
     */
    private boolean hasNoDuplicates(Node x) {
        return false;
    }

    /**
     * 验证是否是二叉查找树的根结点
     *
     * @return {@code true}
     * @see #isBST()
     */
    private boolean isBST() {
        return isBST(root, null, null);
    }

    /**
     * 验证是否是二叉查找树的根结点
     *
     * @param x   root
     * @param min min_value
     * @param max max_value
     * @return {@code true}
     * @see #isBinaryTree(Node)
     * @see #isOrdered(Node, Comparable, Comparable)
     * @see #hasNoDuplicates(Node)
     */
    private boolean isBST(Node x, Key min, Key max) {
//        if (!isBinaryTree(root)) return false;
//        if (!isOrdered(root, min(), max())) return false;
//        return hasNoDuplicates(root);
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }
    // FIXME: 2018/8/18
//    public boolean isRankConsistent() {
//        for (int i = 0; i < size(); i++)
//            if (i != rank(select(i)))
//                return false;
//        for (Key key : keys())
//            if (!key.equals(select(rank(key))))
//                return false;
//        return true;
//    }
//
//    public Iterable<Key> keys() {
//        return keys(min(), max());
//    }
//
//    public Iterable<Key> keys(Key lo, Key hi) {
//        Queue<Key> queue = new Queue<>();
//        keys(root, queue, lo, hi);
//        return queue;
//    }
//
//    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
//        if (x == null) return;
//        int cmplo = lo.compareTo(x.key);
//        int cmphi = hi.compareTo(x.key);
//        if (cmplo < 0) keys(x.left, queue, lo, hi);
//        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
//        if (cmphi > 0) keys(x.right, queue, lo, hi);
//    }
}
