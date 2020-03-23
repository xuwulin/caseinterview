package com.xwl.javase.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author xwl
 * @date 2020-03-16 11:17
 * @description 遍历二叉树
 */
public class TraversalBinaryTree {
    /**
     * 采用递归的方式前序遍历
     */
    public void preOrder(BinaryTree root) {
        if (root != null) {
            System.out.print(root.getData() + "\t");
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }

    /**
     * 采用非递归的方式前序遍历
     */
    public void preOrderNonRecursive(BinaryTree root) {
        Stack<BinaryTree> stack = new Stack<>();
        while (true) {
            while (root != null) {
                System.out.print(root.getData() + "\t");
                stack.push(root);
                root = root.getLeft();
            }
            if (stack.isEmpty()) {
                break;
            }
            root = stack.pop();
            root = root.getRight();
        }
    }

    /**
     * 采用递归的方式中序遍历
     */
    public void inOrder(BinaryTree root) {
        if (root != null) {
            inOrder(root.getLeft());
            System.out.print(root.getData() + "\t");
            inOrder(root.getRight());
        }
    }

    /**
     * 采用非递归的方式中序遍历
     */
    public void inOrderNonRecursive(BinaryTree root) {
        Stack<BinaryTree> stack = new Stack<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
            if (stack.isEmpty()) {
                break;
            }
            root = stack.pop();
            System.out.print(root.getData() + "\t");
            root = root.getRight();
        }
    }

    /**
     * 采用递归的方式后序遍历
     */
    public void postOrder(BinaryTree root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            System.out.print(root.getData() + "\t");
        }
    }

    /**
     * 采用非递归的方式后序遍历
     */
    public void postOrderNonRecursive(BinaryTree root) {
        Stack<BinaryTree> stack = new Stack<>();
        while (true) {
            if (root != null) {
                stack.push(root);
                root = root.getLeft();
            } else {
                if (stack.isEmpty()) {
                    return;
                }

                if (stack.lastElement().getRight() == null) {
                    root = stack.pop();
                    System.out.print(root.getData() + "\t");
                    while (root == stack.lastElement().getRight()) {
                        System.out.print(stack.lastElement().getData() + "\t");
                        root = stack.pop();
                        if (stack.isEmpty()) {
                            break;
                        }
                    }
                }

                if (!stack.isEmpty()) {
                    root = stack.lastElement().getRight();
                } else {
                    root = null;
                }
            }
        }
    }

    /**
     * 层序遍历
     */
    public void levelOrder(BinaryTree root) {
        BinaryTree temp;
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            temp = queue.poll();
            System.out.print(temp.getData() + "\t");
            if (temp.getLeft() != null) {
                queue.offer(temp.getLeft());
            }

            if (temp.getRight() != null) {
                queue.offer(temp.getRight());
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree node10 = new BinaryTree(10, null, null);
        BinaryTree node8 = new BinaryTree(8, null, null);
        BinaryTree node9 = new BinaryTree(9, null, node10);
        BinaryTree node4 = new BinaryTree(4, null, null);
        BinaryTree node5 = new BinaryTree(5, node8, node9);
        BinaryTree node6 = new BinaryTree(6, null, null);
        BinaryTree node7 = new BinaryTree(7, null, null);
        BinaryTree node2 = new BinaryTree(2, node4, node5);
        BinaryTree node3 = new BinaryTree(3, node6, node7);
        BinaryTree node1 = new BinaryTree(1, node2, node3);

        TraversalBinaryTree tree = new TraversalBinaryTree();

        //采用递归的方式进行遍历
        System.out.println("-----前序遍历------");
        System.out.print("递归:\t");
        tree.preOrder(node1);
        System.out.println();

        //采用非递归的方式遍历
        System.out.print("非递归:\t");
        tree.preOrderNonRecursive(node1);
        System.out.println();

        //采用递归的方式进行遍历
        System.out.println("-----中序遍历------");
        System.out.print("递归:\t");
        tree.inOrder(node1);
        System.out.println();

        //采用非递归的方式遍历
        System.out.print("非递归:\t");
        tree.inOrderNonRecursive(node1);
        System.out.println();

        //采用递归的方式进行遍历
        System.out.println("-----后序遍历------");
        System.out.print("递归:\t");
        tree.postOrder(node1);
        System.out.println();

        //采用非递归的方式遍历
        System.out.print("非递归:\t");
        tree.postOrderNonRecursive(node1);
        System.out.println();

        //采用递归的方式进行遍历
        System.out.println("-----层序遍历------");
        System.out.print("递归:\t");
        tree.levelOrder(node1);
        System.out.println();
    }
}
