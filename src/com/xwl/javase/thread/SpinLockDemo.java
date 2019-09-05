package com.xwl.javase.thread;

import java.security.PublicKey;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xwl
 * @date 2019-09-02 20:35
 * @description
 * 题目：实现一个自旋锁
 * 自旋锁的好处：循环比较获取直到成功为止，没有类似wait的阻塞
 *
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒钟，B随后进来后发现
 * 当前有线程持有锁，不是null，所以只能通过自旋等待，直到A释放锁后B随后抢到
 */
public class SpinLockDemo {
    // 原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>(); // 初始值null

    /**
     * 解析：
     * AA线程先运行，进入myLock()方法，atomicReference.compareAndSet(null, thread) 为true,取反为false，故第一次不会进入while
     * 然后AA线程会暂停5秒
     * 主线程在1秒钟后，BB线程进入myLock()方法，此时 atomicReference.compareAndSet(null, thread) 为false，因为此时AA线程
     * 还未结束（暂停5秒）,atomicReference不为null，所以一直在while循环中，
     * 直到5秒以后，AA线程执行myUnLock()方法，并将atomicReference改为null,BB线程跳出while循环，接下来执行myUnLock()方法
     *
     * 执行结果：
     * AA	 come in
     * BB	 come in
     * AA	 invoked myUnLock()
     * BB	 invoked myUnLock()
     *
     * @param args
     */
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            // 暂停一会儿线程
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        }, "AA").start();

        // 让main线程暂停1秒，目的是让AA线程先启动
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        }, "BB").start();
    }

    /**
     * 加锁
     */
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in");
        while (!atomicReference.compareAndSet(null, thread)) { // 第一次肯定不会进入while循环

        }
    }

    /**
     * 解锁
     */
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        // 如果自己用完了，就将其改为null
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoked myUnLock()");
    }
}
