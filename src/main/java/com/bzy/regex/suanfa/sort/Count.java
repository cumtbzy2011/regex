package com.bzy.regex.suanfa.sort;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * @author xinan
 * @date 2021/7/9
 */
public class Count {

    /**
     * 1。申请一个len=max目标数组元素+1的计数数组 2。遍历目标数组，对元素值作为排序数组索引，出现次数为排序数组的值（此为"计数" 3。根据计数数组还原有序数列
     * <p>
     * 计数排序不是比较排序，除了占空间比任何比较排序都快
     */
    public void countSort(int[] target) {
        int[] countTemp = new int[max(target) + 1];
        for (int i : target) {
            countTemp[i]++;
        }
        int targetIndex = 0;
        for (int index = 0; index < countTemp.length; index++) {
            //int数组元素初始化值为0
            while (countTemp[index]-- > 0) {
                target[targetIndex++] = index;
            }
        }
    }

    private int max(int[] target) {
        int max = -1;
        for (int i : target) {
            max = Math.max(i, max);
        }
        return max;
    }

    private int min(int[] target) {
        int min = target[0];
        for (int i : target) {
            min = Math.min(i, min);
        }
        return min;
    }

    /**
     * 桶排序是计数排序的升级版，他用二级数组int[N][]，将数字按范围划分到N个桶中，再使用常规排序算法对桶内数据排序，最后还原有序数列
     * <p>
     * 桶数量越多，性能越好-》极限就是计数排序
     */
    private void bucketSort(int[] target) {
        int bucketSize = 5; //这里限制每个桶大小为5
        int bucketNum = (max(target) - min(target)) / bucketSize + 1; //计算一共要多少个桶
        int[][] buckets = new int[bucketNum][0];
        for (int value : target) {
            int index = (value - min(target)) / bucketSize; //计算value要放入哪个桶（计算索引不要+1
            buckets[index] = add(buckets[index], value);
        }

        int targetIndex = 0;
        for (int[] bucket : buckets) {
            if (bucket.length <= 0) {
                continue;
            }
            new BubblingAndSelect().selectSort(bucket, 0);
            for (int value : bucket) {
                target[targetIndex++] = value;
            }
        }
    }

    private int[] add(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }

    /**
     *
     * 基数排序
     * 1。从低位到高位，根据每一位数字遍历对所有元素排序
     * 2。例如27、35、37，第一次排序35 27 37 第二次排序 27 35 37
     * 3。每次排序，保证高位一致的元素，低位较小的在前面；下一次排序是在这个顺序的基础上再次排的，继承了低位有序性
     */
    public void radixSort(int[] arr) {
        int maxNumLength = getMaxNumLength(arr);
        int[][] temp = new int[10][0];
        for (int i = 1; i <= maxNumLength; i++) {
            for (int j : arr) {
                int digitNum = getByDigit(j, i);
                temp[digitNum - 1] = add(temp[digitNum - 1], j);
            }
            int j = 0;
            for (int[] ints : temp) {
                for (int anInt : ints) {
                    arr[j++] = anInt;
                }
            }
        }
    }

    private int getByDigit(int value, int digit) {
        int i = (int)(value % Math.pow(10, digit));
        return (int)(i / (Math.pow(10, digit - 1)));
    }

    private int getMaxNumLength(int[] values) {
        return String.valueOf(max(values)).length();
    }

    public static void main(String[] args) {
        int[] target = new int[] {5, 4, 4, 3, 5, 6, 2, 1, 9};
        new Count().radixSort(target);
        System.out.println(JSON.toJSONString(target));
    }
}
