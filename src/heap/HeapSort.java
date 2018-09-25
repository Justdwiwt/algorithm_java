package heap;

import java.util.Arrays;

import static heap.HeapOperator.downAdjust;

public class HeapSort {

    /**
     * 堆排序
     *
     * @param array 待调整的堆
     */
    private static void heapSort(int[] array) {
        // 无序数组构建二叉堆
        for (int i = (array.length - 2) / 2; i >= 0; i--)
            downAdjust(array, i, array.length);
        System.out.println(Arrays.toString(array));
        // 循环删除堆顶元素，移到集合尾部，调节堆产生新的堆顶
        for (int i = array.length - 1; i > 0; i--) {
            // 最后一个元素和第一个元素交换
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            // 下沉调整最大堆
            downAdjust(array, 0, i);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
