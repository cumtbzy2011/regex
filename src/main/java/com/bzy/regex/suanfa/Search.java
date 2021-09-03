package com.bzy.regex.suanfa;

/**
 * @author xinan
 * @date 2021/7/1
 */
public class Search {

    public int search(int target, int[] array) {
        int left = 0;
        int right = array.length - 1;
        //right的初始值决定了搜索区间 =》左闭右闭[left，right] =》循环条件有等于
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                right = mid - 1;
            }  else if (array[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int leftBound(int target, int[] array) {
        int left = 0;
        int right = array.length;
        //right的初始值决定了搜索区间 =》左闭右开[left，right） =》循环条件没有等于

        //可以证明一下，这里一定是因为left==right推出循环的
        //left<right进入循环 =》 (left+right)/2 < (right+right)/2 => mid < right => mid+1最多和right相同不能能越过right
        while (left < right) {
            int mid = (left + right) / 2;
            if (array[mid] == target) {
                right = mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else if (array[mid] > target) {
                right = mid;
            }
        }
        //知道退出循环时left=right就很简单了
        return left;
    }

    public int rightBound(int target, int[] array) {
        int left = 0;
        int right = array.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (array[mid] == target) {
                left = mid + 1;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else if (array[mid] > target) {
                right = mid;
            }
        }
        //知道退出循环时left=right就很简单了
        return left -  1;
    }

    //1。数组中有几个数比target大
    //2。数组中有几个数比target小
}
