package com.bzy.regex.suanfa.of;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * 198
 * @author xinan
 * @date 2021/9/1
 */
public class 打家劫舍 {

    /**
     * 这种是递归()而不是正常的动态规划
     * 动态规划=》return dp[N]
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length <= 2) {
            return IntStream.of(nums).max().getAsInt();
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0]; dp[1] = nums[1]; dp[2] = nums[0] + nums[2];
        for (int i = 3; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3]) + nums[i];
        }
        return IntStream.of(dp).max().getAsInt();
    }

    /**
     * 动态规划 =》 字问题定义 =》 求f(k) 的最大值 =》 max(val(k) + f(k-2), f(k-1)))
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if (nums.length <= 2) {
            return IntStream.of(nums).max().getAsInt();
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0]; dp[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }
}
