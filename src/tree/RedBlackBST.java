package tree;

@SuppressWarnings("unused")
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        Key key;
        Value val;
        Node left;
        Node right;
        int N;  //这棵子树中的结点总数
        boolean color;  //父节点指向它的链接的颜色

        Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            N = n;
            this.color = color;
        }
    }

    /**
     * 测试该结点与父结点间链接的颜色
     *
     * @param x Node
     * @return {@code true} RED {@code false} BLACK
     */
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    /**
     * 转换一个结点的两个红色子结点的颜色
     * 红变黑
     *
     * @param h Node
     */
    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    /**
     * 旋转后重置父结点链接
     *
     * @param h Node
     * @return Node
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * 旋转后重置父结点链接
     *
     * @param h Node
     * @return x
     */
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * 返回以该结点为根结点的结点个数
     *
     * @return N
     */
    private int size() {
        return size(root);
    }

    /**
     * 判空
     *
     * @return {@code true}
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean isEmpty() {
        return size() == 0;
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
     * 查找key<br>
     * 找到则更新值
     * 否则新建一个结点
     *
     * @param key Key
     * @param val Value
     * @see #put(Node, Comparable, Object)
     */
    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    /**
     * 查找key<br>
     * 找到则更新值
     * 否则新建一个结点
     *
     * @param h   Node
     * @param key Key
     * @param val Value
     * @return Node
     */
    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, 1, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    /**
     * 平衡结点
     *
     * @param h Node
     * @return Node
     */
    private Node balance(Node h) {
        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    /**
     * 假设结点{@code h}为{@code RED}<br>
     * {@code h.left}和{@code h.left.left}都是{@code BLACK}<br>
     * 将{@code h.left}和{@code h.left}的子结点之一变为{@code RED}
     *
     * @param h Node
     * @return Node
     */
    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }

    /**
     * 删除最小键
     *
     * @see #deleteMin(Node)
     */
    @SuppressWarnings("ConstantConditions")
    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    /**
     * 删除最小键
     *
     * @param h Node
     * @return 平衡后结点
     * @see #balance
     */
    private Node deleteMin(Node h) {
        if (h.left == null) return null;
        if (!isRed(h.left) && !isRed(h.left.right))
            h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balance(h);
    }

    /**
     * 假设结点{@code h}为{@code RED}<br>
     * {@code h.right}和{@code h.right.left}都是{@code BLACK}<br>
     * 将{@code h.right}和{@code h.right}的子结点之一变为{@code RED}
     *
     * @param h Node
     * @return Node
     */
    private Node moveRedRight(Node h) {
        flipColors(h);
        if (!isRed(h.left.left)) h = rotateRight(h);
        return h;
    }

    /**
     * 删除最大键
     *
     * @see #deleteMax(Node)
     */
    @SuppressWarnings("ConstantConditions")
    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    /**
     * 删除最大键
     *
     * @param h Node
     * @return 平衡后结点
     * @see #balance
     */
    private Node deleteMax(Node h) {
        if (isRed(h.left)) h = rotateRight(h);
        if (h.right == null) return null;
        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }

    /**
     * 删除结点
     *
     * @param key Key
     * @see #delete(Node, Comparable)
     */
    @SuppressWarnings("ConstantConditions")
    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    /**
     * 删除结点
     *
     * @param h   Node
     * @param key Key
     * @return 平衡后结点
     */
    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)) h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                h.val = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            } else h.right = delete(h.right, key);
        }
        return balance(h);
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
     * @return {@code true} Value <br> {@code false} null
     */
    @SuppressWarnings("Duplicates")
    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }
}
