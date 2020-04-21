package com.example.aboutview;

public class Col {
    public static void main(String[] args) {
        int[] array = {2, 1};
        headSort(array);
        for (int num : array)
            System.out.println(num);
    }

    private void sort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            tree(array, array.length, i);
        }

        int temp;
        for (int i = array.length - 1; i > 0; i--) {
            temp = array[i];
            array[i] = array[0];
            array[0] = temp;

            tree(array, i, 0);
        }
    }

    private void tree(int[] array, int length, int root) {
        int current = root;
        int child = current * 2 + 1;
        int temp = array[current];

        while (child < length) {
            if (child + 1 < length) {
                if (array[child + 1] > array[child]) {
                    child = child + 1;
                }
            }
            if (temp >= array[child]) break;

            array[current] = array[child];
            current = child;
            child = child * 2 + 1;
        }
        array[child] = temp;
    }

    private static void headAdjust(int[] list, int len, int i) {
        int current = i, temp = list[i], child = 2 * current + 1;
        while (child < len) {
            if (child + 1 < len) {
                if (list[child] < list[child + 1]) {
                    child = child + 1;
                }
            }
            if (list[child] > temp) {
                list[current] = list[child];
                current = child;
                child = 2 * current + 1;
            } else {
                break;
            }
        }
        list[current] = temp;
    }


    /**
     * 堆排序
     */
    public static void headSort(int[] list) {
        //构造初始堆,从第一个非叶子节点开始调整,左右孩子节点中较大的交换到父节点中
        for (int i = (list.length) / 2 - 1; i >= 0; i--) {
            headAdjust(list, list.length, i);
        }
        //排序，将最大的节点放在堆尾，然后从根节点重新调整
        for (int i = list.length - 1; i >= 1; i--) {
            int temp = list[0];
            list[0] = list[i];
            list[i] = temp;
            headAdjust(list, i, 0);
        }
    }


    //-------------------------------------------------------------------
    //快排

    public static int divide(int[] data, int start, int end) {
        int base = data[end];
        while (start < end) {
            while (start < end && data[start] <= base) {
                //从左边开始遍历，如果比基准值小，就继续向右走
                start++;
            }
            //上面的while循环结束时，就说明当前的a[start]的值比基准值大，应与基准值进行交换
            if (start < end) {
                //交换
                int temp = data[start];
                data[start] = data[end];
                data[end] = temp;
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值右边)，因此右边也要同时向前移动一位
                end--;
            }
            while (start < end && data[end] >= base) {
                //从右边开始遍历，如果比基准值大，就继续向左走
                end--;
            }
            //上面的while循环结束时，就说明当前的a[end]的值比基准值小，应与基准值进行交换
            if (start < end) {
                //交换
                int temp = data[start];
                data[start] = data[end];
                data[end] = temp;
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值左边)，因此左边也要同时向后移动一位
                start++;
            }
        }
        //这里返回start或者end皆可，此时的start和end都为基准值所在的位置
        return end;
    }

    /**
     * 快速排序
     * <p>
     * 时间复杂度：O（n^2）
     *
     * @param data
     * @param start
     * @param end
     */

    public static void quickSort(int[] data, int start, int end) {
        if (start > end) {
            //如果只有一个元素，就不用再排下去了
            return;
        } else {
            //如果不止一个元素，继续划分两边递归排序下去
            int partition = divide(data, start, end);
            quickSort(data, start, partition - 1);
            quickSort(data, partition + 1, end);
        }
    }
}
