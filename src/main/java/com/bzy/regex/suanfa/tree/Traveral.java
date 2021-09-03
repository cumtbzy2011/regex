package com.bzy.regex.suanfa.tree;

import java.util.LinkedList;

/**
 *  使用栈stack可以不用递归遍历二叉树
 */
public class Traveral {

    /**
     * 前序遍历：根-> 左-> 右
     */
    public void preOrderTraveral(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.println(treeNode.getDate());
        preOrderTraveral(treeNode.getLeft());
        preOrderTraveral(treeNode.getRight());
    }

    /**
     * 中序遍历：左-> 根-> 右
     */
    public void inOrderTraveral(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        inOrderTraveral(treeNode);
        System.out.println(treeNode.getDate());
        inOrderTraveral(treeNode.getRight());
    }

    /**
     * 后序遍历：左-> 右-> 根
     */
    public void postOrderTraveral(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        postOrderTraveral(treeNode.getLeft());
        postOrderTraveral(treeNode.getRight());
        System.out.println(treeNode.getDate());
    }

    /**
     * 层序遍历
     */
    public void levelOrder(TreeNode node) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode root = queue.poll();
            System.out.println(root.getDate());
            if (root.getLeft() != null) {
                queue.add(root.getLeft());
            }
            if (root.getRight() != null) {
                queue.add(root.getRight());
            }
        }
    }
}
