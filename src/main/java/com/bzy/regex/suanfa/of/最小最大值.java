package com.bzy.regex.suanfa.of;

/**
 * @author xinan
 * @date 2021/8/31
 */
public class 最小最大值 {

    public static void main(String[] args) {
        int minMax = new 最小最大值().minMax(new int[] {1, 2, 3, 4, 5}, 16);
        System.out.println(minMax);
    }

    public int minMax(int[] nums, int k) {
        int max = max(nums);
        int gapSum = 0;
        for (int i = 0; i < nums.length; i++) {
            gapSum += max - nums[i];
        }
        if (gapSum >= k) {
            return max;
        } else {
            int adds = k - gapSum;
            return max  + (adds / nums.length) + (adds % nums.length == 0 ? 0 : 1);
        }
    }

    public int max(int[] nums) {
        int max = nums[0];
        for (int num : nums) {
            max = Math.max(num, max);
        }
        return max;
    }
}
