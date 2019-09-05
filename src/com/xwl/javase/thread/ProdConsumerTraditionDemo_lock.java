package com.xwl.javase.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：一个初始值为0的变量 两个线程交替操作 一个加1 一个减1，来5轮
 * 就是生产者与消费者案例！即生产一个消费一个。
 *
 * 方式一：传统方式！！
 * 关键：
 * 1.   线程    操作      资源类
 * 2.   判断   干活       通知
 * 3.防止虚假唤醒机制（使用while，不能使用if!!!）
 **/
public class ProdConsumerTraditionDemo_lock {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.deIncrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
    }
}

/**
 * 共享资源类
 */
class ShareData {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            // 1.判断
            while (num != 0) {
                // 等待，不能生产
                condition.await();
            }
            // 2.干活
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            // 3.通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void deIncrement() throws Exception {
        lock.lock();
        try {
            // 1.判断
            while (num == 0) {
                // 等待，不能生产
                condition.await();
            }
            // 2.干活
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            // 3.通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}