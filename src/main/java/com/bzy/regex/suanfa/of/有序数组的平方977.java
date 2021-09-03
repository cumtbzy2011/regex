package com.bzy.regex.suanfa.of;

/**
 * @author xinan
 * @date 2021/9/3
 */
public class 有序数组的平方977 {

    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int i = 0;
        for (; i < nums.length - 1; i++) {
            if (nums[i] < 0 && nums[i+1] >= 0) {
                break;
            }
        }
        int index = 0;
        int k = i;
        for (int j = i+1; j < nums.length; j++) {
            int j2 = (int)Math.pow(nums[j], 2);
            for (; k >= 0; k--) {
                int k2 = (int)Math.pow(nums[k], 2);
                if (k2 <= j2) {
                    result[index++] = k2;
                } else {
                    break;
                }
            }
            result[index++] = j2;
        }
        return result;
    }
}
