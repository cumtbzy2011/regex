package com.bzy.regex.suanfa.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author xinan
 * @date 2021/7/12
 */
public class Heap {

    public void sort(int[] target) {
        //1. 构建初始化大顶堆
        buildMaxHeap(target);
        int len = target.length;
        for (int i = target.length - 1; i > 0 ; i--) {
            //2. 将最大元素移动到队尾
            swap(target, 0, i);
            //3. 对剩下元素维持大顶堆，由于这里把0-head节点数据变了，要从head节点开始调整堆
            heapify(target, 0, --len);
        }
    }

    /**
     * 从下向上、从左向右构建大顶堆（如果从上向下构建，左子树可能导致head变化，而head变化会导致右树变化，即左右树变化会相互影响
     * 准确的说是从最后一个非叶子节点向前遍历构建，左右树是交替进行的，并不是严格从下向上、从左向右
     */
    private void buildMaxHeap(int[] target) {
        //可以证明len/2一定是数组中最后一个非叶子节点，从最后一个非叶子节点向前遍历构建大顶堆
        for (int i = (target.length / 2); i >= 0; i--) {
            heapify(target, i, target.length);
        }
    }

    /**
     * 1. 一个二叉树可以由头节点代表，这里的i就是头节点，该函数作用是使得该头节点代表的二叉树变为大顶堆
     */
    private void heapify(int[] target, int i, int len) {
        int left = 2 * i + 1; //左右节点index公示要记住
        int right = 2 * i + 2;
        int head = i;

        //由于这里left/right是由计算获得，要防止越界; 大顶堆不是二叉搜索树所有这里都是 > head
        if (left < len && target[left] > target[head]) {
            head = left;
        }
        if (right < len && target[right] > target[head]) {
            head = right;
        }
        //可以看出这里只旋转了一次，只要保证最大值在最上即可（大顶堆不是二叉搜索树，不保证右数一定大于左数，只保证head大于左右
        if (head != i) {
            swap(target, i, head);
            //这里移动了的节点可能导致树不再是大顶堆，所以要处理下维持是大顶堆
            heapify(target, head, len);
        }
    }

    private void swap(int[] target, int i, int j) {
        int temp = target[i];
        target[i] = target[j];
        target[j] = temp;
    }

    public static void main(String[] args) {
        int[] target = new int[] {5, 4, 4, 3, 5, 6, 2, 1, 9};
        new Heap().sort(target);
        System.out.println(JSON.toJSONString(target));
    }
}
