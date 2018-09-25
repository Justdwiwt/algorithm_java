package tree;

@SuppressWarnings("unused")
interface ST<Key extends Comparable<Key>, Value> {
//    /**
//     * 创建一张有序符号表
//     */
//    ST();

    /**
     * 将键值对存入表中
     * 若值为空则将键key从表中删除
     *
     * @param key 键
     * @param val 值
     */
    void put(Key key, Value val);

    /**
     * 获取键key对应的值
     * 若键key不存在则返回空
     *
     * @param key 键
     * @return value
     */
    Value get(Key key);

    /**
     * 从表中删去key及对应的value
     *
     * @param key 键
     */
    void delete(Key key);

    /**
     * 键key是否存在于表中
     *
     * @param key 键
     * @return {@code true}
     */
    boolean contains(Key key);

    /**
     * 表是否为空
     *
     * @return {@code true}
     */
    boolean isEmpty();

    /**
     * 表中的键值对数量
     *
     * @return size
     */
    int size();

    /**
     * 最小的键
     *
     * @return key
     */
    Key min();

    /**
     * 最大的键
     *
     * @return key
     */
    Key max();

    /**
     * 小于等于key的最大键
     *
     * @param key 键
     * @return tmp <= key
     */
    Key floor(Key key);

    /**
     * 大于等于key的最小键
     *
     * @param key 键
     * @return tmp >= key
     */
    Key ceiling(Key key);

    /**
     * 小于key的键的数量
     *
     * @param key 键
     * @return count
     */
    int rank(Key key);

    /**
     * 排名为k的键
     *
     * @param k index
     * @return key
     */
    Key select(int k);

    /**
     * 删除最小的键
     */
    void deleteMin();

    /**
     * 删除最大的键
     */
    void deleteMax();

    /**
     * [lo...hi]之间键的数量
     *
     * @param lo 低位
     * @param hi 高位
     * @return count
     */
    int size(Key lo, Key hi);

    /**
     * [lo...hi]之间的所有键
     * 键已排序
     *
     * @param lo 低位
     * @param hi 高位
     * @return key.sort()
     */
    Iterable<Key> keys(Key lo, Key hi);

    /**
     * 表中所有键的集合
     * 键已排序
     *
     * @return key.sort()
     */
    Iterable<Key> keys();

}
