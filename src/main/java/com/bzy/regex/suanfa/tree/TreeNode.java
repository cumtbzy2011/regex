package com.bzy.regex.suanfa.tree;

/**
 * @author xinan
 * @date 2021/7/12
 */
public class TreeNode {
    private int date;
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int date, TreeNode parent, TreeNode left, TreeNode right) {
        this.date = date;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public int getDate() {
        return date;
    }

    public TreeNode setDate(int date) {
        this.date = date;
        return this;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode setLeft(TreeNode left) {
        this.left = left;
        return this;
    }

    public TreeNode getRight() {
        return right;
    }

    public TreeNode setRight(TreeNode right) {
        this.right = right;
        return this;
    }

    public TreeNode getParent() {
        return parent;
    }

    public TreeNode setParent(TreeNode parent) {
        this.parent = parent;
        return this;
    }
}
