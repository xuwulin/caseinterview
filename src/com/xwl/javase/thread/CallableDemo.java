package com.xwl.javase.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author xwl
 * @date 2019-09-03 16:04
 * @description
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 两个线程，一个main线程，一个是AA线程的futureTask

        // FutureTask(Callable<V> callable)
        FutureTask<Integer> futureTask = new FutureTask<>(new myThread3());
        new Thread(futureTask, "AA").start();
        // 多个线程使用同一个FutureTask，最终结果也只有一次，
        // 想要有多个结果，则应该创建多个FutureTask
//        FutureTask<Integer> futureTask2 = new FutureTask<>(new myThread3());
        new Thread(futureTask, "BB").start();

        System.out.println(Thread.currentThread().getName() + "****************");

        int res2 = 100;
//        while (!futureTask.isDone()) {
//
//        }

        // 要求获得callable线程的计算结果，如果没有计算完成就要去强求，会导致阻塞，直到计算完成
        Integer res = futureTask.get(); // 建议放在最后
        System.out.println("***********result: " + (res + res2) + "*************");

        // 查看本机线程数
        System.out.println(Runtime.getRuntime().availableProcessors()); // 8
    }
}

/**
 * 创建线程方式一：继承Thread类
 */
class myThread extends Thread {

}

/**
 * 创建线程方式二：实现Runnable接口
 */
class myThread2 implements Runnable {

    @Override
    public void run() {

    }
}

/**
 * 创建线程方式三：实现Callable接口
 * 带返回值的线程，并且能抛出异常
 *
 * 为什么有了Runnable还需要Callable呢？
 * 因为并发、异步最终导致Callable的出现
 */
class myThread3 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("******************come in callable****************");
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}