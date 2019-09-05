package com.xwl.javase.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone2 implements Runnable {
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    private void get() {
        lock.lock();
        lock.lock(); // 只要锁有lock和unlock，并且匹配，加几把都可以，也就是加锁几次，就要解锁几次
        try {
            System.out.println(Thread.currentThread().getName() + "\tget");
            set();
        } finally {
            lock.unlock();
            lock.unlock(); // 只要锁有lock和unlock，并且匹配，加几把都可以，也就是加锁几次，就要解锁几次
        }
    }

    private void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\tset");
        } finally {
            lock.unlock();
        }
    }
}

/**
 * Description:
 * 可重入锁(也叫做递归锁)
 * 指的是同一先生外层函数获得锁后,内层敌对函数任然能获取该锁的代码
 * 在同一线程外外层方法获取锁的时候,在进入内层方法会自动获取锁
 * <p>
 * 也就是说,线程可以进入任何一个它已经标记的锁所同步的代码块
 *
 **/
public class ReenterLockDemo2 {
    /**
     * t3	get
     * t3	set
     * t4	get
     * t4	set
     * @param args
     */
    public static void main(String[] args) {
        Phone2 phone2 = new Phone2();
        Thread t3 = new Thread(phone2, "t3");
        Thread t4 = new Thread(phone2, "t4");
        t3.start();
        t4.start();

    }
}
 
 
