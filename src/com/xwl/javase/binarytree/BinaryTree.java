package com.xwl.javase.binarytree;

/**
 * @author xwl
 * @date 2020-03-16 11:16
 * @description
 */
public class BinaryTree {
    private int data;  // 节点的值
    private BinaryTree left; // 左子节点
    private BinaryTree right; // 右子节点

    public BinaryTree(int data, BinaryTree left, BinaryTree right) {
        super();
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public BinaryTree getRight() {
        return right;
    }
}
