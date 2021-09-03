package com.bzy.regex.suanfa.tree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author xinan
 * @date 2021/7/13
 */
public class BalanceTree {

    private TreeNode root;

    public TreeNode rightRotation(TreeNode node) {
        TreeNode parent = node.getParent();
        TreeNode left = node.getLeft();
        TreeNode leftRight = left.getRight();

        if (leftRight != null) {
            leftRight.setParent(node);
        }

        node.setParent(left);
        node.setLeft(leftRight);

        left.setParent(parent);
        left.setRight(node);

        if (parent == null) {
            root = left;
        } else if (parent.getLeft() == node) {
            parent.setLeft(left);
        } else if (parent.getRight() == node) {
            parent.setRight(left);
        }
        return left;
    }

    public TreeNode leftRotation(TreeNode node) {
        TreeNode parent = node.getParent();
        TreeNode right = node.getRight();
        TreeNode rightLeft = right.getLeft();
        //左旋右旋分别有三个节点变化
        //1. 右节点的左树比右节点小，要维持在有节点的左树（右树本来就大不用动
        if (rightLeft != null) {
            rightLeft.setParent(node);
        }
        //2. 根节点变成原来右节点的左树
        node.setRight(rightLeft);
        node.setParent(right);
        //3. 右节点变成根节点
        right.setLeft(node);
        right.setParent(parent);
        if (parent == null) {
            root = right;
        } else if (parent.getRight() == node) {
            parent.setRight(right);
        } else if (parent.getLeft() == node) {
            parent.setLeft(right);
        }

        return right;
    }

    public boolean put(int data) {
        return putData(root, data);
    }

    private boolean putData(TreeNode root, int data) {
        if (root == null) {
            this.root = new TreeNode(data, null, null, null);
            return true;
        }
        TreeNode node = root;
        TreeNode target = node;
        while (node != null) {
            target = node;
            if (node.getDate() < data) {
                node = node.getRight();
            } else if (node.getDate() > data) {
                node = node.getLeft();
            } else {
                return false;
            }
        }
        if (target.getDate() < data) {
            target.setRight(new TreeNode(data, target, null, null));
        } else if (target.getDate() > data) {
            target.setLeft(new TreeNode(data, target, null, null));
        }

        rebuild(target);
        return true;
    }

    public void rebuild(TreeNode node) {
        while (node != null) {
            int bf = getDepth(node.getLeft()) - getDepth(node.getRight());
            if (bf == 2) {
                int sbf = getDepth(node.getLeft().getLeft()) - getDepth(node.getLeft().getRight());
                if (sbf == -1) {
                    //左右-左子节点先左旋，节点再右旋
                    leftRotation(node.getLeft());
                }
                rightRotation(node);
            } else if (bf == -2) {
                int sbf = getDepth(node.getRight().getLeft()) - getDepth(node.getRight().getRight());
                if (sbf == 1) {
                    //右左-右子节点先右旋，节点再左旋
                    rightRotation(node.getRight());
                }
                leftRotation(node);
            } else {
                node = node.getParent();
            }
        }
    }

    private int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = getDepth(node.getLeft());
        int rightDepth = getDepth(node.getRight());
        return 1 + Math.max(leftDepth, rightDepth);
    }

    public void print() {
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        System.out.println(root.getDate());
        while (!nodes.isEmpty()) {
            TreeNode headNode = nodes.poll();
            TreeNode leftNode = headNode.getLeft();
            if (leftNode != null) {
                System.out.print(leftNode.getDate() + " ");
                nodes.offer(leftNode);
            }
            TreeNode rightNode = headNode.getRight();
            if (rightNode != null) {
                System.out.println(rightNode.getDate() + " ");
                nodes.offer(rightNode);
            }
        }
    }

    public boolean delete(int data) {
        //1. 找到要删除的节点
        TreeNode node = this.root;
        TreeNode deleteNode = null;
        while (node != null) {
            int nodeData = node.getDate();
            if (nodeData == data) {
                deleteNode = node;
                break;
            } else if (nodeData > data) {
                node = node.getLeft();
            } else if (nodeData < data) {
                node = node.getRight();
            }
        }
        if (deleteNode == null) {
            return false;
        }
        //2. 找到后继节点-右子树的最小节点替换
        TreeNode replaceNode = findMinNode(deleteNode.getRight());
        deleteNode.setDate(replaceNode.getDate());
        //3. 替换后删除后继节点
        TreeNode replaceRight = replaceNode.getRight();
        TreeNode parent = replaceNode.getParent();
        if (parent.getRight() == replaceNode) {
            parent.setRight(replaceRight);
        } else if (parent.getLeft() == replaceNode) {
            parent.setLeft(replaceRight);
        }
        if (replaceRight != null) {
            replaceRight.setParent(parent);
        }
        //4. 删除后平衡树
        rebuild(parent);
        return true;
    }


    private TreeNode findMinNode(TreeNode node) {
        TreeNode minNode = node;
        TreeNode temp = node;
        while (temp != null) {
            minNode = temp;
            temp = temp.getLeft();
        }
        return minNode;
    }

    public static void main(String[] args) {
        BalanceTree tree = new BalanceTree();
        Arrays.asList(11, 4, 9, 7, 15, 10)
            .forEach(i -> {
                System.out.println(tree.put(i));
            });
        tree.delete(9);
        tree.print();
    }
}
