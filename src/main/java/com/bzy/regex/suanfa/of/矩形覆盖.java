package com.bzy.regex.suanfa.of;

/**
 * 用2*1的小矩形横着或者竖着去覆盖2*N的矩形，有几种覆盖方法
 *
 * @author xinan
 * @date 2021/9/2
 */
public class 矩形覆盖 {

    //1。 如果第一个位置竖着放，那么剩下的空间有2*(k-1)，所以有dp(k-1)种方式
    //2。 如果第一个位置横着放，那么第二个位置也要横着放，那么剩下空间为2*(k-2)，所以有dp(k-2)种方式
    //3。 两种情况加起来得到转移方程：dp[i] = dp[i-1] + dp[i-2]
}
