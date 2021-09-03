package com.bzy.regex.suanfa.sort;

import com.alibaba.fastjson.JSON;

/**
 * 1。 把一个数组拆成两个，对两边分别进行排序（当拆成最小，只有一个元素的数组就是有序数组）
 * 2。 对排序好的两个子数组进行合并，并将合并好的有序数列写入元数组
 * 3。 归并时需要临时数组存放有序数列，最后将临时数组写入原数组
 */
public class Merge {

    public void sort(int[] target) {
        //1. 每次归并都需要一个临时数组存放排序数据，再把排序好的数据写回元数组，这里直接申请了一个最长的临时数组（空间一定够）避免每次递归都要开辟临时数组
        int[] temp = new int[target.length];
        sort(target, 0, target.length-1, temp);
    }

    private void sort(int[] target, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(target, left, mid, temp);
            sort(target, mid+1, right, temp);
            merge(target, left, mid, right, temp);
        }
    }

    private void merge(int[] target, int left, int mid, int right, int[] temp) {
        int firstLeftIndex = left;
        int firstRightIndex = mid;
        int secondLeftIndex = mid + 1;
        int secondRightIndex = right;
        int firstIndex = firstLeftIndex;
        int secondIndex = secondLeftIndex;

        int tempIndex = 0;
        while (firstIndex <= firstRightIndex && secondIndex <= secondRightIndex) {
            if (target[firstIndex] <= target[secondIndex]) {
                temp[tempIndex++] = target[firstIndex++];
            } else {
                temp[tempIndex++] = target[secondIndex++];
            }
        }
        while (firstIndex <= firstRightIndex) {
            temp[tempIndex++] = target[firstIndex++];
        }
        while (secondIndex <= secondRightIndex) {
            temp[tempIndex++] = target[secondIndex++];
        }

        int t = 0;
        while (t < tempIndex) {
            target[left++] = temp[t++];
        }
    }



    public static void main(String[] args) {
        int[] target = new int[] {5, 4, 4, 3, 5, 6, 2, 1, 9};
        new Merge().sort(target);
        System.out.println(JSON.toJSONString(target));
    }
}
