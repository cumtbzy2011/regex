package com.bzy.regex.suanfa.of;

import java.util.stream.IntStream;

/**
 * @author xinan
 * @date 2021/9/3
 */
public class 只出现一次的数字 {


    public int singleNumber(int[] nums) {
        //1。暴力搜索O(n2)
        //2. 快排O(nlogn)
        //3. hash表 空间O(N)
        //4. 异或位运算
        return IntStream.of(nums)
            .reduce((i, i2) -> i ^ i2)
            .getAsInt();
    }


    private int[] sort(int[] nums) {
        return null;
    }
}
