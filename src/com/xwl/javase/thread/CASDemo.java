package com.xwl.javase.thread;

import javax.smartcardio.ATR;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xwl
 * @date 2019-09-02 15:20
 * @description
 * 1.CAS是什么？ ===> compareAndSwap 比较并计算
 *
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5); // 主物理内存中的值是5
        // compareAndSet的两个参数，第一个表示期望值是5，第二个表示更新值是2019
        // 当主内存中的值 == 期望值时，将值改为2019
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t current data:" + atomicInteger.get()); // true	 current data:2019
        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\t current data:" + atomicInteger.get()); // false	 current data:2019
        atomicInteger.getAndIncrement();
    }
}
