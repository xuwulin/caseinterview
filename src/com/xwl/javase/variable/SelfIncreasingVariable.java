package com.xwl.javase.variable;

/**
 * @author xwl
 * @date 2019-08-12 23:47
 * @description 自增变量
 */
public class SelfIncreasingVariable {
    public static void main(String[] args) {
        int i = 1; // i在局部变量表中，值为1
        // i++ ++操作要后算，所以先把i的值（为1）压入操作数栈，再执行i++使局部变量表中的i变为2，
        // 然后执行赋值操作，将操作数栈中的i（值为1）赋值给局部变量表中的i，所以i仍然为1
        i = i++; // i = 1
        int j = i++; // j = 1  i = 2
        // 此时局部变量表中的i 和操作数栈中的i均为2
        // 先执行 ++i,使局部变量表中的i变为3，并压入操作数栈；再执行 i++ 去局部变量表中取i的值(3)，并压入操作数栈
        // 然后i再执行自增，使局部变量表中的i变为4
        // 所以k = 2 + 3 * 3
        int k = i + ++i * i++; // 此时 = 右边的 i ++i i++均在操作数栈中
        System.out.println("i=" + i);
        System.out.println("i=" + j);
        System.out.println("i=" + k);
    }
}
