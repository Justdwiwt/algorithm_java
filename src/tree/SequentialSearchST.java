package tree;

@SuppressWarnings("unused")
public class SequentialSearchST<Key, Value> {
    private Node first; //链表首结点

    /**
     * 定义列表
     * 顺序查找 基于无序链表
     */
    private class Node {
        Key key;
        Value val;
        Node next;

        Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 查找给定的键
     * 返回相关联的值
     *
     * @param key key
     * @return value
     */
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.val;
        return null;
    }

    /**
     * 查找给定的键
     * 找到更新
     * 否则新建结点
     *
     * @param key key
     * @param val value
     */
    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        first = new Node(key, val, first);
    }
}
