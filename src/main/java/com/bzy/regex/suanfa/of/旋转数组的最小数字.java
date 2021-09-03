package com.bzy.regex.suanfa.of;

/**
 * @author xinan
 * @date 2021/8/30
 */
public class 旋转数组的最小数字 {

    public static void main(String[] args) {
        System.out.println(new 旋转数组的最小数字().minNumberInRotateArray(new int[]{2,2,2,0,1}));
    }

    public int minNumberInRotateArray(int [] array) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            if (array[left] > array[right]) {
                //有旋转
                int mid = (left+right)/2;
                if (array[mid] >= array[left]) {
                    left = mid + 1;
                } else if (array[mid] < array[left]){
                    right = mid;
                }
            } else if (array[left] < array[right]) {
                //没旋转
                return array[left];
            } else {
                int min = array[left];
                for (int i = left; i <= right ; i++) {
                    if (min > array[i]) {
                        min = array[i];
                    }
                }
                return min;
            }
        }
        return array[right];
    }

}