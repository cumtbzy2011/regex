package com.bzy.regex.suanfa.of;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 1. 确定dp数组及其下标含义
 * 	i-层数 dp[i]-方法
 * 2. 确定递推公式
 * 	dp[i] = dp[i-1] + dp[i-2]
 * 3. 确定如何初始化（先确定递推公式再考虑初始化）
 * 	dp[1] = 1; dp[2] = 2
 * 4. 确定遍历顺序
 * 	根据递推公式dp[i] = dp[i-1] + dp[i-2]；从前向后遍历
 * 5. 举例推导dp数组
 *
 * 如何debug动态规划代码：打印dp数组看序列是否符合推导过程。
 *
 *
 *
 *
 * dp[i][j] (i-行;j-列) dp[i][j]-最小路径和
 * dp[i][j] = min(dp[i+1][j], dp[i+1][j+1]) + val[i][j]
 *
 * dp[n] = val[n]
 * 由于这里遵守的转移函数是i+1的，所以从后向前遍历；也可以从头向尾遍历 dp[i][j] = min(dp[i-1][j], dp[i-1][j-1]) + val[i][j]
 *
 * @author xinan
 * @date 2021/9/1
 */
public class 最小路径 {

    public static void main(String[] args) {

    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        List<List<Integer>> dp = new ArrayList<>(triangle);
        dp.set(triangle.size() - 1, triangle.get(triangle.size() - 1));
        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> tempDp = new ArrayList<>(triangle.get(i));
            dp.set(i, tempDp);
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int dpIj = Math.min(dp.get(i + 1).get(j), dp.get(i + 1).get(j + 1)) + triangle.get(i).get(j);
                dp.get(i).set(j, dpIj);
            }
        }
        return dp.get(0).get(0);
    }

}
