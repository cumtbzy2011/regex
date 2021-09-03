package com.bzy.regex.suanfa.sort;

import com.alibaba.fastjson.JSON;

/**
 * 插入排序：
 *  a：双层遍历，第一层正向遍历所有元素放入第二层处理；第二层反向遍历已经排序好的元素
 *  b：第二层从后向前遍历有序数组时，将比目标值大的元素向后移动一位，这样最后会空出一位就是要插入的位置
 *  c: 要关注0的情况（对于这种有+1 or -1双步长遍历数组的情况，要格外注意边界情况
 */
public class InsertAndShell {

    public void insertSort(int[] target) {
        //1. i从1开始，因为0元素不需要排序，实际上也进入不了第二层循环
        //第一层是要插入的数
        for (int i = 1; i < target.length; i++) {
            int thisValue = target[i];
            int j;
            //2. 插入排序是从后向前比较，目标数是[i]，那么要比较的数就是从[i-1]开始的
            //第二层是要比较的数，0当然也包含在内
            for (j = i - 1; j >= 0; j--) {
                if (target[j] > thisValue) {
                    target[j + 1] = target[j];
                } else {
                    break;
                }
            }
            target[j + 1] = thisValue;
        }
    }
    // 将move融入遍历可以-logN的复杂度
    private void move(int[] target, int start, int end) {
        for (int i = end; i >= start; i--) {
            target[end + 1] = target[end];
        }
    }

    private void swap(int[] target, int sourceIndex, int targetIndex) {
        target[sourceIndex] = target[sourceIndex] + target[targetIndex];
        target[targetIndex] =  target[sourceIndex] - target[targetIndex];
        target[sourceIndex] = target[sourceIndex] - target[targetIndex];
    }

    /**
     * a. 三层遍历，最外层加一个gap折叠遍历，gap最后一个值要是1
     * b. 内部插入排序代码完全一致，只是gap从默认的1变成了动态的gap
     *
     */
    public void shellSort(int[] target) {
        for (int gap = target.length/2; gap > 0; gap /= 2) {

            //1. 从这里开始进行插入排序，可以看出代码和插入排序完全一样，只是gap从1变成动态的
            //实际上这里i从0开始也可以，只不过i<gap时并不会进入下一级循环，相当于无效
            for (int i = gap; i < target.length; i++) {
                int temp = target[i];
                int j;
                //2. 插入排序是从后向前比较，目标数是[i]，那么要比较的数就是从[i-gap]开始的
                for (j = i-gap; j >=0; j = j-gap) {
                    if (temp < target[j]) {
                        target[j + gap] = target[j];
                    } else {
                        //3. 遇到比目标数小的数，说明目标数要插入到这个数之后
                         break;
                    }
                }
                //4. 将目标数插入到这个数之后，因为在退出循环时j-gap已经发生了，所以这里加一个
                target[j+gap] = temp;
            }
        }
    }


    public static void main(String[] args) {
        int[] array = new int[] {5, 11, 7, 9, 1, 3, 5, 6, 6, 5};
        new InsertAndShell().insertSort(array);
        System.out.println(JSON.toJSONString(array));
    }

}
