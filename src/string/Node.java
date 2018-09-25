package string;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class Node implements Comparable<Node> {
    private char ch;
    private int freq;
    private final Node left;
    private final Node right;

    /**
     * 单词查找树
     *
     * @param ch    char
     * @param freq  int
     * @param left  Node
     * @param right Node
     */
    private Node(char ch, int freq, Node left, Node right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @SuppressWarnings("PointlessArithmeticExpression")
    @Override
    public int compareTo(Node that) {
        return that.freq - that.freq;
    }
}
