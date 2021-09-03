package com.bzy.regex.suanfa.of;

import java.util.Arrays;
import java.util.Stack;

/**
 * 重建二叉树
 * 是树的结构，一般都是用递归来实现。
 *
 * @author xinan
 * @date 2021/8/30
 */
public class BuildTree {

    public static void main(String[] args) {
        int[] pre = new int[] {1,2,4,7,3,5,6,8};    //前序遍历结果
        int[] vin = new int[] {4,7,2,1,5,3,8,6};    //中序遍历结果
        TreeNode treeNode = new BuildTree().reConstructBinaryTree(pre, vin);
        prePrint(treeNode);
    }

    public static void prePrint(TreeNode rootNode) {
        if (rootNode == null) {
            return;
        }
        System.out.println(rootNode.val);
        prePrint(rootNode.left);
        prePrint(rootNode.right);
    }

    /**
     * 1. root = pre[] + vin[]  1.1 root = left + right + val
     * 2. root = (leftPre[] + leftVin[] ) + (rightPre[] + rightPre[] ) + val
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] vin) {
        if (pre.length == 0) {
            return null;
        }
        int rootValue = pre[0];
        TreeNode rootNode = new TreeNode(rootValue);
        int rootVinIndex = indexOf(rootValue, vin);
        int[] leftVin = safeCopyOfRange(vin, 0, rootVinIndex);
        int[] rightVin = safeCopyOfRange(vin, rootVinIndex + 1, vin.length);
        int[] leftPre = safeCopyOfRange(pre, 1, leftVin.length + 1);
        int[] rightPre = safeCopyOfRange(pre, leftVin.length + 1, pre.length);
        TreeNode leftRootNode = reConstructBinaryTree(leftPre, leftVin);
        TreeNode rightRootNode = reConstructBinaryTree(rightPre, rightVin);
        rootNode.left = leftRootNode;
        rootNode.right = rightRootNode;
        return rootNode;
    }

    private int[] safeCopyOfRange(int[] target, int from, int to) {
        if (from > to || from >= target.length || to > target.length) {
            return new int[0];
        } else {
            return Arrays.copyOfRange(target, from, to);
        }
    }

    private int indexOf(int value, int[] vin) {
        for (int i = 0; i < vin.length; i++) {
            if (vin[i] == value) {
                return i;
            }
        }
        return -1;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}