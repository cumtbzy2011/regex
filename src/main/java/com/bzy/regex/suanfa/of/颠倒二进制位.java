package com.bzy.regex.suanfa.of;

/**
 * @author xinan
 * @date 2021/9/3
 */
public class 颠倒二进制位 {
    public int reverseBits(int n) {
        int result = 0;
        int i =  32;
        while (i-- != 0) {
            result = result << 1;
            result = result + ( n & 1);
            n = n >> 1;
        }
        return result;
    }
}
