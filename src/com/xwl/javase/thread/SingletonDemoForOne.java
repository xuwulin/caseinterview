package com.xwl.javase.thread;

/**
 * 单例模式，单机版（main线程）
 */
public class SingletonDemoForOne {

    private static SingletonDemoForOne instance = null;

    // 构造器，独一份
    private SingletonDemoForOne() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法SingletonDemoForOne()");
    }

    /**
     * 双重检测机制
     *
     * @return
     */
    public static SingletonDemoForOne getInstance() {
        if (instance == null) {
            instance = new SingletonDemoForOne();
        }
        return instance;
    }

    public static void main(String[] args) {

        //  单机版（main线程）的单例模式
//        System.out.println(SingletonDemoForOne.getInstance() == SingletonDemoForOne.getInstance()); // true
//        System.out.println(SingletonDemoForOne.getInstance() == SingletonDemoForOne.getInstance()); // true
//        System.out.println(SingletonDemoForOne.getInstance() == SingletonDemoForOne.getInstance()); // true
//
//        System.out.println("=========================================");

        // 并发多线程后，情况发生了很大的变化，会打印出多条，并且每次运行结果都不一样
//        1	 我是构造方法SingletonDemoForOne()
//        3	 我是构造方法SingletonDemoForOne()
//        2	 我是构造方法SingletonDemoForOne()
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> SingletonDemoForOne.getInstance(), String.valueOf(i)).start();
        }
    }
}