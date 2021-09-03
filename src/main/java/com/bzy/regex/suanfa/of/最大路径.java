package com.bzy.regex.suanfa.of;

/**
 * @author xinan
 * @date 2021/9/1
 */
public class 最大路径 {

    public static void main(String[] args) {

    }

    public int maxPath(int[] array) {
        return maxPath(array, 1, 1); //其实就是求顶点最大路径
    }

    /**
     *
     * @param array 原始数组
     * @param i 第几行，从1开始
     * @param j 第几列，从1开始
     * @return
     */
    private int maxPath(int[] array, int i, int j) {
        int indexIj = getIndex(i, j);
        if (indexIj >= array.length) {
            return 0;
        }
        int maxLeftDown = maxPath(array, i + 1, j);
        int maxRightDown = maxPath(array, i + 1, j + 1);
        return Math.max(maxLeftDown, maxRightDown) + array[indexIj];
    }

    /**
     * Dij 的索引显然是i-1排最后一个节点的索引 + j
     * i-1最后一个元素索引是三角形所有元素个数，三角形每一行是=1的等差数列，所以很好计算
     * @param i
     * @param j
     * @return
     */
    private int getIndex(int i, int j) {
        int sum = 0;
        for (int k = 0; k < i; k++) {
            sum += k;
        }
        return sum + j;
    }
}
