package com.xwl.javase.thread;

import org.omg.SendingContext.RunTime;

import java.util.concurrent.*;

/**
 * @author xwl
 * @date 2019-09-03 16:59
 * @description 第四种获得/使用java多线程的方式：线程池
 * Array  工具类-->  Arrays
 * Collection  工具类-->   collections
 * Executor  工具类-->    Executors
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors()); // 获取CPU的核心数

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3), // LinkedBlockingDeque如果不赋初值，则默认最大值Integer.MAX_VALUE
                Executors.defaultThreadFactory(), // 表示生成线程池中工作线程的线程工厂,用户创建新线程,一般用默认即可
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略，AbortPolicy(默认):直接抛出RejectedException异常阻止系统正常运行
//                new ThreadPoolExecutor.CallerRunsPolicy() // "调用者运行"一种调节机制,该策略既不会抛弃任务,也不会抛出异常,而是将某些任务回退到调用者
//                new ThreadPoolExecutor.DiscardOldestPolicy() // 抛弃队列中等待最久的任务,然后把当前任务加入队列中尝试再次提交
//                new ThreadPoolExecutor.DiscardPolicy() // 直接丢弃任务,不予任何处理也不抛出异常.如果允许任务丢失,这是最好的拒绝策略
        );

        // 模拟用户来办理业务，每个用户就是一个来自外部的请求线程
        // 根据以上配置，最多5 + 3 = 8（线程最大数+阻塞队列数）个用户办理业务，超过8个则会抛异常：java.util.concurrent.RejectedExecutionException
        try {
            for (int i = 1; i <= 8 ; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    /**
     * 生产中都不会使用，jdk的创建线程池
     */
    public static void threadPoolInit() {
        // public interface Executor
        // public interface ExecutorService extends Executor
//        ExecutorService threadPool = Executors.newFixedThreadPool(5); // 一池5个处理线程，可理解为一个银行的5个窗口
//        ExecutorService threadPool = Executors.newSingleThreadExecutor(); // 一池1个处理线程
        ExecutorService threadPool = Executors.newCachedThreadPool(); // 一池 N 个处理线程

        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 1; i <= 10 ; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
