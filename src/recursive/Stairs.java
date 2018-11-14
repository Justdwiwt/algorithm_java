package recursive;

import java.util.HashMap;
import java.util.Map;

/**
 * 爬楼梯问题
 * <p>每次迈一步或两步</p>
 * <p>求解n个台阶的走法</p>
 *
 * @author Justdwiwt
 */
public class Stairs {

    /**
     * key 是 n，value 是 fun(n)
     *
     * @param n 台阶数
     * @return 方法数
     */
    @SuppressWarnings("ConstantConditions")
    private static int fun(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        Map<Integer, Integer> hasSolvedList = new HashMap<>();
        if (hasSolvedList.containsKey(n)) return hasSolvedList.get(n);

        int ret = fun(n - 1) + fun(n - 2);
        hasSolvedList.put(n, ret);
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(fun(10));
    }

}
