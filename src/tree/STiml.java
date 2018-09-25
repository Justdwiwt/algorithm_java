package tree;

@SuppressWarnings({"unchecked", "unused"})
public abstract class STiml implements ST {
    /**
     * [lo...hi]之间键的数量
     *
     * @param lo 低位
     * @param hi 高位
     * @return count
     */
    @Override
    public int size(Comparable lo, Comparable hi) {
        if (hi.compareTo(lo) < 0)
            return 0;
        else if (contains(hi))
            return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    /**
     * 删除最小的键
     */
    @Override
    public void deleteMin() {
        delete(min());
    }

    /**
     * 删除最大的键
     */
    @Override
    public void deleteMax() {
        delete(min());
    }

    /**
     * 表中所有键的集合
     * 键已排序
     *
     * @return key.sort()
     */
    @Override
    public Iterable keys() {
        return keys(min(), max());
    }
}
