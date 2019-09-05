package com.xwl.javase.thread;

/**
 * 单例模式，高并发下的写法
 */
public class SingletonDemo {

    // 加上关键字 volatile，防止指令重排序
    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法SingletonDemo()");
    }

    /**
     * DCL（Double Check Lock 双端检测机制）模式：在加锁的前后都进行判断
     *
     * @return
     */
    public static SingletonDemo getInstance() { // 尽量不要在方法上使用synchronized关键字，会将整个方法锁住
        if (instance == null) { // 加锁前判断
            synchronized (SingletonDemo.class) { // 同步代码块加锁
                if (instance == null) { // 加锁后判断
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        // 并发多线程后，情况发生了很大的变化
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}