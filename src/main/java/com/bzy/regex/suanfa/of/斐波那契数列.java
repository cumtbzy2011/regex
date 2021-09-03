package com.bzy.regex.suanfa.of;

/**
 * @author xinan
 * @date 2021/8/31
 */
public class 斐波那契数列 {

    public static void main(String[] args) {
        System.out.println(new 斐波那契数列().indexOf(4));
    }

    public int indexOf(int index) {
        if (index == 0) {
            return 0;
        }
        if (index == 1) {
            return 1;
        }
        return indexOf(index - 1) + indexOf(index - 2);
    }
}
