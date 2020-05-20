package com.example.suanfa;

import android.util.Log;

import java.lang.ref.WeakReference;

public class Sort {

    public static void main(String[] args) {
        int[] array = new int[]{5, 3, 2, 7, 1, 4, 5, 5, 10, 1, 6};
        kuaipai(array, 0, array.length - 1);
        for (int i : array) {
            System.out.println(i);
        }

        Singleton st = new Singleton();
        WeakReference wrf = new WeakReference(st);
        Singleton.Model model = st.model;


        st = null;
        Runtime.getRuntime().gc();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new AssertionError();
        }
        System.runFinalization();

        System.out.println("weak = what ?" + wrf.get());
        System.out.println("model = what ?" + model);


    }

    private static void maopao(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j + 1] < array[j]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    private static void xuanze(int[] array) {
        int min = 0;
        int minIndex = 0;
        int temp = 0;

        for (int i = 0; i < array.length - 1; i++) {
            min = array[i];
            minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }

            if (i == minIndex) continue;

            temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    private static void charu(int[] array) {

        int temp = 0;

        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
    }

    private static void xieer(int[] array) {
        int temp = 0;
        for (int i = array.length / 2; i > 0; i /= 2) {
            for (int j = i; j < array.length; j++) {
                for (int k = j; k > 0 && k - i >= 0; k -= i) {
                    if (array[k] < array[k - 1]) {
                        temp = array[k];
                        array[k] = array[k - 1];
                        array[k - 1] = temp;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private static void kuaipai(int[] array, int low, int high) {


        if (low >= high) return;

        int start = low;
        int end = high;
        int midValue = array[low];
        boolean flag = true;

        while (true) {
            if (flag) {
                if (array[end] > midValue) {
                    end--;
                } else {
                    array[start] = array[end];
                    start++;
                    flag = false;
                }
            } else {
                if (array[start] < midValue) {
                    start++;
                } else {
                    array[end] = array[start];
                    end--;
                    flag = true;
                }
            }

            if (start == end) {
                array[start] = midValue;
                break;
            }

        }

        kuaipai(array, low, start - 1);
        kuaipai(array, start + 1, high);

    }

    public static void mergeSort(int[] arr, int start, int end) {
        //判断拆分的不为最小单位
        if (end - start > 0) {
            //再一次拆分，知道拆成一个一个的数据
            mergeSort(arr, start, (start + end) / 2);
            mergeSort(arr, (start + end) / 2 + 1, end);
            //记录开始/结束位置
            int left = start;
            int right = (start + end) / 2 + 1;
            //记录每个小单位的排序结果
            int index = 0;
            int[] result = new int[end - start + 1];
            //如果查分后的两块数据，都还存在
            while (left <= (start + end) / 2 && right <= end) {
                //比较两块数据的大小，然后赋值，并且移动下标
                if (arr[left] <= arr[right]) {
                    result[index] = arr[left];
                    left++;
                } else {
                    result[index] = arr[right];
                    right++;
                }
                //移动单位记录的下标
                index++;
            }
            //当某一块数据不存在了时
            while (left <= (start + end) / 2 || right <= end) {
                //直接赋值到记录下标
                if (left <= (start + end) / 2) {
                    result[index] = arr[left];
                    left++;
                } else {
                    result[index] = arr[right];
                    right++;
                }
                index++;
            }
            //最后将新的数据赋值给原来的列表，并且是对应分块后的下标。
            for (int i = start; i <= end; i++) {
                arr[i] = result[i - start];
            }
        }
    }
}
