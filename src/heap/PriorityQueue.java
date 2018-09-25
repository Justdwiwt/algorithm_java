package heap;

import java.util.Arrays;

public class PriorityQueue {

    private int[] array;
    private int size;

    private PriorityQueue() {
        // 队列初始长度32
        array = new int[32];
    }

    /**
     * 入队
     *
     * @param key 入队元素
     */
    private void enQueue(int key) {
        // 队列长度超出范围，扩容
        if (size >= array.length)
            resize();
        array[size++] = key;
        upAdjust();
    }

    /**
     * 出队
     *
     * @return 堆顶元素
     * @throws Exception Exception
     */
    private int deQueue() throws Exception {
        if (size <= 0)
            throw new Exception("the queue is empty!");
        // 获取堆顶元素
        int head = array[0];
        array[0] = array[--size];
        downAdjust();
        return head;
    }

    /**
     * 上浮调整
     */
    private void upAdjust() {
        int childIndex = size - 1;
        int parentIndex = childIndex / 2;
        int temp = array[childIndex];
        while (childIndex > 0 && temp > array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = parentIndex / 2;
        }
        array[childIndex] = temp;
    }

    /**
     * 下沉调整
     */
    private void downAdjust() {
        int parentIndex = 0;
        int temp = array[parentIndex];
        int childIndex = 1;
        while (childIndex < size) {
            if (childIndex + 1 < size && array[childIndex + 1] > array[childIndex])
                childIndex++;
            if (temp >= array[childIndex])
                break;
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 下沉调整
     */
    private void resize() {
        int newSize = this.size * 2;
        this.array = Arrays.copyOf(this.array, newSize);
    }

    public static void main(String[] args) throws Exception {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(10);
        priorityQueue.enQueue(2);
        priorityQueue.enQueue(7);
        System.out.println("出队元素：" + priorityQueue.deQueue());
        System.out.println("出队元素：" + priorityQueue.deQueue());
    }

}
