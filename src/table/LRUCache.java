package table;

import java.util.HashMap;

/**
 * LRU算法
 *
 * @author Justdwiwt
 */
public class LRUCache {
    private Node head;
    private Node end;
    //缓存存储上限
    private int limit;

    private HashMap<String, Node> hashMap;

    private LRUCache(int limit) {
        this.limit = limit;
        hashMap = new HashMap<>();
    }

    private String get(String key) {
        Node node = hashMap.get(key);
        if (node == null) return null;
        refreshNode(node);
        return node.value;
    }


    private void put(String key, String value) {
        Node node = hashMap.get(key);
        if (node == null) {
            if (hashMap.size() >= limit) {
                String oldKey = removeNode(head);
                hashMap.remove(oldKey);
            }
            node = new Node(key, value);
            addNode(node);
            hashMap.put(key, node);
        } else {
            node.value = value;
            refreshNode(node);
        }
    }

    @SuppressWarnings("unused")
    public void remove(String key) {
        Node node = hashMap.get(key);
        removeNode(node);
        hashMap.remove(key);
    }

    /**
     * 刷新被访问的节点位置
     *
     * @param node 被访问的节点
     */
    private void refreshNode(Node node) {
        if (node == end) return;
        removeNode(node);
        addNode(node);
    }

    /**
     * 删除节点
     *
     * @param node 要删除的节点
     * @return 删除结点的值
     */
    private String removeNode(Node node) {
        if (node == end)
            end = end.pre;
        else if (node == head)
            head = head.next;
        else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    /**
     * 尾部插入节点
     *
     * @param node 要插入的节点
     */
    private void addNode(Node node) {
        if (end != null) {
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
        if (head == null) head = node;
    }

    class Node {
        Node(String key, String value) {
            this.key = key;
            this.value = value;
        }

        Node pre;
        Node next;
        public String key;
        String value;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        lruCache.put("001", "test1");
        lruCache.put("002", "test1");
        lruCache.put("003", "test1");
        lruCache.put("004", "test1");
        lruCache.put("005", "test1");
        lruCache.get("002");
        lruCache.put("004", "test2");
        lruCache.put("006", "test6");
        System.out.println(lruCache.get("001"));
        System.out.println(lruCache.get("006"));
    }

}
