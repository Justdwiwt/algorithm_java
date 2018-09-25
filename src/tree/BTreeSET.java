package tree;

@SuppressWarnings("unused")
public class BTreeSET<Key extends Comparable<Key>> extends Page {
    private Page root = new Page(true);

    public BTreeSET(Key sentinel) {
        put(sentinel);
    }

    public boolean contains(Key key) {
        return contains(root, key);
    }

    @SuppressWarnings("unchecked")
    private boolean contains(Page h, Key key) {
        if (h.isExternal()) return h.contains(key);
        return contains(h.next(key), key);
    }

    public void add(Key key) {
        put(root, key);
        if (root.isFull()) {
            Page lefthalf = root;
            Page righthalf = root.split();
            root = new Page(false);
            root.put(lefthalf);
            root.put(righthalf);
        }
    }

    @SuppressWarnings("unchecked")
    public void add(Page h, Key key) {
        if (h.isExternal()) {
            h.put(key);
            return;
        }
        Page next = h.next(key);
        put(next, key);
        if (next.isFull()) h.put(next.split());
        next.close();
    }

    private void put(Key key) {
    }

    private void put(Page h, Key key) {
    }
}
