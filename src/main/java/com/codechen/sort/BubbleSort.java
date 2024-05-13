package com.codechen.sort;

import java.util.Arrays;

/**
 * @author：Java陈序员
 * @date：2024/1/21 21:49
 * @description：冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {

        // 冒泡排序时间复杂度：O(n^2)
//        int array[] = {3, 9, -1, 10, -2};
        int array[] = {3, 9, -1, 10, 20};
//        int array[] = {1, 2, 3, 4, 5};
//        int array[] = {5, 4, 3, 2, 1};

        int temp = 0;
        for (int i = 0; i < array.length - 1; i++) {
            int count = 0;
            for (int j = 0; j < array.length - 1 - i; j++) {

                if (array[j] > array[j + 1]) {
                    count++;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }

            }

            if (count == 0) {
                break;
            }

            System.out.printf("第 %d 躺排序后的数组：%n", i + 1);
            System.out.println(Arrays.toString(array));
        }
    }
}
