package sort;

import java.util.Arrays;

/**
 * 睡眠排序
 *
 * @author Justdwiwt
 */
public class Sleep {

    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 8, 9, 3, 2};
        SleepSort[] sleepSorts = new SleepSort[arr.length];
        Arrays.setAll(sleepSorts, i -> new SleepSort(arr[i]));
        Arrays.stream(sleepSorts).forEachOrdered(Thread::start);
    }

}

/**
 * SleepSort
 * <p>不能处理负数</p>
 * <p>数据过近存在误差</p>
 *
 * @author Justdwiwt
 */
class SleepSort extends Thread {
    private int num;

    SleepSort(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        try {
            sleep(num * 10 + 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(num + " ");
    }

}