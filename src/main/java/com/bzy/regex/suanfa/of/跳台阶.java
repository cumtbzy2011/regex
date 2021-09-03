package com.bzy.regex.suanfa.of;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xinan
 * @date 2021/8/31
 */
public class 跳台阶 {

    public static void main(String[] args) {
        System.out.println(new 跳台阶().numWays(44));
    }

    private Map<Integer, Long> cache = new HashMap<>();
    public long numWays(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (cache.get(n) != null) {
            return cache.get(n);
        }
        long result = numWays(n-1) + numWays(n-2);
        cache.put(n, result);
        return result;
    }
}
