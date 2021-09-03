package com.bzy.regex.suanfa.sort;

import com.alibaba.fastjson.JSON;
import com.sun.tools.javac.util.ArrayUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

/**
 * 双指针、分冶递归、for for嵌套循环
 * 1。分冶递归：a:选择最左元素为随机基准值，遍历移动元素使得基准值处于数组中间（其右边数都>=他；其左边数都<=他。
 *          b:然后将左右两边数组分别递归执行a操作
 *          c:a直到传入的子数组只有一个元素时此时不需要操作直接返回
 * 2。双指针（for emptyIndex：
 *          a：以最左元素为基准值，此时用临时变量保存基准值，则数组左边空了一个空间
 *          b：从右边遍历数组，找到比基准值小的数，移动到左边的空闲空间，则数组右边空了一个空间
 *          c：从左边遍历数据，找到比基准值大的数，移动到右边的空闲空间，则数组左边空了一个空间，则跳出左循环继续右循环
 *          d：保证左指针<右指指针
 *
 *          emptyIndex总是等于leftIndex或rightIndex，可以简化代码，这里为了好理解还是用了emptyIndex变量
 */
public class Quick {

    public void sort(int[] target, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = pivot(target, left, right);
        sort(target, left, pivot - 1);
        sort(target, pivot + 1, right);
    }

    public int pivot(int[] target, int left, int right) {
        int pivotValue = target[left];
        int leftIndex = left;

        for (int rightIndex = right; rightIndex > leftIndex; rightIndex--) {
            if (target[rightIndex] < pivotValue) {
                target[leftIndex] = target[rightIndex];
                for (; ++leftIndex < rightIndex; ) {
                    if (target[leftIndex] > pivotValue) {
                        target[rightIndex] = target[leftIndex];
                        break;
                    }
                }
            }

        }
        target[leftIndex] = pivotValue;
        return leftIndex;
    }

    public static void main(String[] args) {
        int[] target = new int[] {5, 4, 4, 3, 5, 6, 2, 1, 9};
        new Quick().sort(target, 0, target.length - 1);
        System.out.println(JSON.toJSONString(target));
    }

    public String replaceSpace (String s) {
        char[] value = new char[s.length() * 3];
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                value[index++] = s.charAt(i);
            } else {
                value[index++] = '%';
                value[index++] = '2';
                value[index++] = '0';
            }
        }
        return String.valueOf(value, 0, index);
    }
}
