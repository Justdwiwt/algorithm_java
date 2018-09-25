package tree;

@SuppressWarnings("unused")
class Page<Key> {
    Page(boolean bottom) {
    }

    Page() {
    }

    void close() {
    }

    void add(Key key) {
    }

    void add(Page P) {
    }

    boolean isExternal() {
        return false;
    }

    boolean contains(Key key) {
        return false;
    }

    Page next(Key key) {
        return null;
    }

    boolean isFull() {
        return false;
    }

    Page split() {
        return null;
    }

    Iterable<Key> keys() {
        return null;
    }

    void put(Page h) {
    }

    void put(Key key) {
    }
}
