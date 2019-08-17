package com.xwl.javase.operation;

import java.util.Random;

/**
 * @author xwl
 * @date 2019-08-16 21:10
 * @description Java移位操作符以及按位操作符
 * <p>
 * 按位操作符：针对两个整数参数中的对应位执行布尔代数运算，并生成一个结果。
 * 按位与&：如果两个输入位都是1则生成一个输出位1，否则生成一个输出位0。
 * 按位或|：如果两个输入位只要有一位是1则生成一个输出位1.否则生成一个输出位为0。
 * 按位异或^：如果两个输入位只有其中一位是1那么生成输出位1，否则生成一个输出位为0。
 * 按位非~：取反，输入0则输出1，输入1则输出0。
 * <p>
 * 移位操作符：只可用来处理整数类型。
 * <<：左移位操作符，按照操作符右侧指定的位数将操作符左边的操作数左移，低位补0。
 * >>：右移位操作符，按照操作符右侧指定的位数将操作符左边的操作数右移，如果符号为正高插入0，如符号为负高位插入1。
 * >>>：无符号右移位操作符，跟右移位操作符功能一样，差别在于无论正负高位插入0。
 * <<=，>>=，>>>=：表示将移位后的结果重新赋给左边。此时有精度问题有可能得到的不是正确的值。根据基本类型来看，从低精度到高精度依次为：
 * byte>short>int>long>float>double。如果低精度到高精度的话，例如byte或short进行右移位运算，会先被转成Int类型，再进行移位，然后截尾赋值给原理的类型。
 */
public class JavaOperation {
    public static void main(String[] args) {
//        testBitwiseOperation();
        testShiftedBitOperation();
    }

    /**
     * 按位操作
     */
    public static void testBitwiseOperation() {
        Random rand = new Random();
        int i = rand.nextInt(Integer.MAX_VALUE);
        int j = rand.nextInt(Integer.MAX_VALUE);
        System.out.println("i = " + i);
        System.out.println("j = " + j);
        System.out.println("i(binary) = " + Integer.toBinaryString(i));
        System.out.println("j(binary) = " + Integer.toBinaryString(j));
        System.out.println("i & j = " + Integer.toBinaryString(i & j));
        System.out.println("i & j(int) = " + (i & j));
        System.out.println("i | j = " + Integer.toBinaryString(i | j));
        System.out.println("i | j(int) = " + (i | j));
        System.out.println("i ^ j = " + Integer.toBinaryString(i ^ j));
        System.out.println("i ^ j(int) = " + (i ^ j));
        System.out.println("~ i = " + Integer.toBinaryString(~i));
        System.out.println("~ i(int) = " + (~i));
    }

    /**
     * 移位操作
     * 左移的规则只记住一点：丢弃最高位，0补最低位（左边第一位为最高位，右边第一位为最低位）
     * 右移的规则只记住一点：符号位不变，左边补上符号位
     *
     * 11 >> 2 (11为int型)
     * 1)11的二进制形式为：0000 0000 0000 0000 0000 0000 0000 1011
     * 2)把低位的最后两个数字移出，因为该数字是正数，所以在高位补零。
     * 3)最终结果是：0000 0000 0000 0000 0000 0000 0000 0010
     */
    public static void testShiftedBitOperation() {
        int i = 1;
        System.out.println("i = " + i); // 1
        // int 类型占4个字节，一个字节是8位，故1的二进制32位为：0000 0000 0000 0000 0000 0000 0000 0001 ==> 2º = 1
        // 0000 0000 0000 0000 0000 0000 0000 0011 ==> 2º + 2¹ = 3
        // 规则：从右往左 第一位为：2º，第二位为：2¹，第三位为：2²。。。
        System.out.println("i(binary) = " + Integer.toBinaryString(i)); // i(binary) = 1
        // 往左移10位 ：0000 0000 0000 0000 0000 0100 0000 0000
        System.out.println("i << 10 = " + (i << 10)); // i << 10 = 1024 即 2的10次幂 = 1024
        System.out.println("i << 10(binary) = " + Integer.toBinaryString(i << 10)); // 0000 0000 0000 0000 0000 0100 0000 0000
        System.out.println("i >> 10 = " + (i >> 10)); // 1 >> 10 = 0
        System.out.println("i >> 10(binary) = " + Integer.toBinaryString(i >> 10)); // 0000 0000 0000 0000 0000 0000 0000 0000
        System.out.println("i >>> 10(binary) = " + Integer.toBinaryString(i >>> 10));

        System.out.println("********<<=,>>=,<<<=,>>>=***************");
        i = -1;
        System.out.println("i(binary) = " + Integer.toBinaryString(i)); // 11111111111111111111111111111111
        i >>>= 10;
        System.out.println("i(binary) = " + Integer.toBinaryString(i)); // 1111111111111111111111
        // long 是8字节，64位
        long j = -1;
        System.out.println("j(binary) = " + Long.toBinaryString(j)); // 1111111111111111111111111111111111111111111111111111111111111111
        j >>>= 10;
        System.out.println("j(binary) = " + Long.toBinaryString(j)); // 111111111111111111111111111111111111111111111111111111
        byte k = -1;
        System.out.println("k(binary) = " + Integer.toBinaryString(k));
        k >>>= 10;
        System.out.println("k(binary) = " + Integer.toBinaryString(k));
        short s = -1;
        System.out.println("s(binary) = " + Integer.toBinaryString(s));
        k >>>= 10;
        System.out.println("s(binary) = " + Integer.toBinaryString(s));
        // 跟前面的 k >>>=10对比，可以看出因为精度问题得到的结果不同
        k = -1;
        System.out.println("k(binary) = " + Integer.toBinaryString(k));
        System.out.println("k(binary) = " + Integer.toBinaryString(k >>> 10));
    }
}
