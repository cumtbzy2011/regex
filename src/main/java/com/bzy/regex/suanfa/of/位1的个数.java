package com.bzy.regex.suanfa.of;

/**
 * @author xinan
 * @date 2021/9/2
 */
public class 位1的个数 {

    public static void main(String[] args) {
        System.out.println(new 位1的个数().isPowerOfTwo(1));
        //System.out.println(Integer.toBinaryString(0));
    }

    public int hammingWeight(int n) {
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            sum += n & 1;
            n = n >>> 1;
        }
        return sum;
    }

    /**
     * 是否是2的幂
     **/
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            sum += n & 1;
            n = n >> 1;
        }
        return sum == 1;


        //return n > 0 && (n & n - 1) == 0;
        //return n > 0 && (n & -n) == n;
    }
}
