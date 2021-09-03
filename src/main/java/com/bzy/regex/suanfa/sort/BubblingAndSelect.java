package com.bzy.regex.suanfa.sort;

import com.alibaba.fastjson.JSON;

/**
 * 冒泡：
 * 循环、递归
 * a: 每次循环都从头开始，保证（交换）相邻两个元素大小顺序，循环结束后最大值会移动到数组最后
 * b: 对剩下的数组递归执行上述操作
 *
 * 选择：
 * 循环、递归
 * a：每次遍历所有元素，找到最小的一个放在最前面，交换最小值和最前面元素（i+1）的位置
 * b：对剩下的数组递归执行上述操作（和冒泡的区别是冒泡有交换，省了一个minIndex的空间
 */
public class BubblingAndSelect {

    public void bubblingSort(int[] target, int rightIndex) {
        if (rightIndex == 0) {
            return;
        }
        for (int i = 1; i <= rightIndex; i++) {
            if (target[i - 1] > target[i]) {
                swap(target, i - 1, i);
            }
        }
        bubblingSort(target, rightIndex - 1);
    }

    public void selectSort(int[] target, int leftIndex) {
        if (leftIndex >= target.length - 1) {
            return;
        }

        int minIndex = leftIndex;
        for (int i = leftIndex; i < target.length; i++) {
            if (target[i] < target[minIndex]) {
                minIndex = i;
            }
        }
        swap(target, leftIndex, minIndex);
        selectSort(target, leftIndex+1);
    }

    private void swap(int[] target, int sourceIndex, int targetIndex) {
        int sourceValue = target[sourceIndex];
        target[sourceIndex] = target[targetIndex];
        target[targetIndex] = sourceValue;
    }


    public static void main(String[] args) {
        int[] array = new int[] {5, 11, 7, 9, 1, 3, 5, 6, 6, 5};
        int[] array2 = new int[] {5, 11, 7, 9, 1, 3, 5, 6, 6, 5};
        new BubblingAndSelect().bubblingSort(array, array.length-1);
        System.out.println(JSON.toJSONString(array));
        new BubblingAndSelect().selectSort(array2, 0);
        System.out.println(JSON.toJSONString(array2));
    }
}
