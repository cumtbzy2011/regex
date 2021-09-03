package com.bzy.regex.suanfa.of;

/**
 * @author xinan
 * @date 2021/9/1
 */
public class 首先出错版本 {

    public static void main(String[] args) {
        System.out.println(new 首先出错版本().firstBadVersion(5, 4));
    }

    public int firstBadVersion(int n, int target) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid, target)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private boolean isBadVersion(int version, int target) {
        return version >= target;
    }
}
