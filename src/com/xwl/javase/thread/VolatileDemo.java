package com.xwl.javase.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xwl
 * @date 2019-09-02 11:18
 * @description
 * 1、验证volatile的可见性
 *  1.1 假如 int number = 0; ，number变量之前根本没有添加 volatile 关键字修饰,即没有可见性
 *  1.2 volatile int number = 0; volatile关键字增强了主内存和各线程内存的可见性，
 *  只要有一个线程改变了主内存中的值，其他线程会马上收到通知，即添加了volatile，可以解决可见性问题
 *
 * 2、验证volatile 不保证原子性
 *  2.1 原子性指的是什么意思？
 *      不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。
 *      需要整体完整，要么同时成功，要么同时失败
 *  2.2 不保证原子性，会出现写丢失的情况
 *  2.3 why? 线程太快，后面的线程把前面的线程的值覆盖掉，导致出现丢失写值得情况
 *  2.4 如何解决原子性？
 *      1） 使用synchronized关键字，但不建议使用，太重
 *      2） 使用我们的juc下的AtomicInteger --》主要使用 CAS 算法
 *
 *  原子变量与CAS算法（CAS效率高）
 *  * 一、i++ 的原子性问题：i++ 的操作实际上分为三个步骤“读-改-写”
 *  * int i = 10;
 *  * i = i++; // 10
 *  * <p>
 *  * int temp = i; // 读
 *  * i = i + 1; // 写
 *  * i = temp; // 改
 *  * <p>
 *  *
 *  * 二、原子变量：在 java.util.concurrent.atomic 包下提供了一些原子变量。
 *  * 1. volatile 保证内存可见性
 *  * 2. CAS（Compare-And-Swap） 算法保证数据变量的原子性
 *  * CAS 算法是硬件对于并发操作的支持
 *  * CAS 包含了三个操作数：
 *  * ①内存值  V
 *  * ②预估值  A
 *  * ③更新值  B
 *  * 当且仅当 V == A 时， V = B（将B的值赋值给V）; 否则，不会执行任何操作。
 *
 */
public class VolatileDemo {
    public static void main(String[] args) { // main是一切方法的运行入口
//        seeOkByVolatile();
        testAtomoic();
    }

    /**
     * 1、volatile可以保证内存可见性，及时通知其他线程，主物理内存的值已被修改
     */
    public static void seeOkByVolatile() {
        MyData myData = new MyData();
        // 第一个线程
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                // 暂停一会儿，模拟计算时间
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60(); // 修改number的值
            System.out.println(Thread.currentThread().getName() + "\t update number value" + myData.number);
        }, "AAA").start();

        // 第二个线程就是我们的main线程
        while (myData.number == 0) {
            // 如果number未使用volatile关键字修饰，主内存中的number始终是0
            // main线程会一直在这里等待循环，直到number值不再等于零
            // 但使用volatile修饰后，主内存中number的值改变，马上会通知其他线程
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over，main get number value is " + myData.number);
    }

    /**
     * 2、volatile不保证原子性
     * 解决方法：在 addPlusPlus 方法上加关键字 synchronized 解决原子性问题，但是 synchronized太重，一般不要轻易使用
     */
    public static void testAtomoic() {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus(); // 不能解决原子性问题
                    myData.addMyAtomic(); // 解决原子性问题
                }
            }, String.valueOf(i)).start();
        }

        // 需要等待上面20个线程都全部计算完成后，再使用main线程取得最终的结果值看是多少
        // 暂停一会儿
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        while (Thread.activeCount() > 2) { // 后台默认有2个线程，一个是main，二是后台GC线程
            // yield 礼让线程，让出CPU给其他线程，等待20个线程全部计算完成
            Thread.yield();
        }
        // 每次运行所得的结果不一致，数据安全问题
        System.out.println(Thread.currentThread().getName() + "\t int type, finally number value:" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t atomicInteger type, finally number value:" + myData.atomicInteger);
    }
}

class MyData {
//    int number = 0;
    volatile int number = 0;
    public void addTo60() {
        this.number = 60;
    }

    // 请注意，此时number前面是加了volatile关键字修饰的，volatile不保证原子性
    public void addPlusPlus() { // 可以加 synchronized关键字，解决原子性问题
        number ++;
    }

    // 使用 AtomicInteger 解决原子性问题
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic() {
        atomicInteger.getAndIncrement(); // 相当于i++
    }
}