package com.xwl.javase.singleton;

import java.util.concurrent.*;

/**
 * 懒汉式：延迟创建这个实例对象
 * 方式一：线程不安全（适用于单线程）
 *
 * 要点：
 * (1)构造器私有化
 * (2)用一个静态变量保存这个唯一的实例
 * (3)提供一个静态方法，获取这个实例对象
 */
public class Singleton4 {
	// (2)用一个静态变量保存这个唯一的实例
    private static Singleton4 instance;

    // (1)构造器私有化
    private Singleton4() {}

    // (3)提供一个静态方法，获取这个实例对象
    public static Singleton4 getInstance() {
    	// 创建一次后就无需再创建
        if (instance == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Singleton4();
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
		/*
		Singleton4 si1 = Singleton4.getInstance();
		Singleton4 si2 = Singleton4.getInstance();
		System.out.println(si1 == si2); // true
		System.out.println(si1); // com.xwl.javase.singleton.Singleton4@1540e19d
		System.out.println(si2); // com.xwl.javase.singleton.Singleton4@1540e19d
		*/

        Callable<Singleton4> c = new Callable<Singleton4>() {
            @Override
            public Singleton4 call() throws Exception {
                return Singleton4.getInstance();
            }
        };

        // 创建线程池
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton4> f1 = es.submit(c);
        Future<Singleton4> f2 = es.submit(c);

        Singleton4 s1 = f1.get();
        Singleton4 s2 = f2.get();

        // 可能使true,也可能是false（线程安全问题）
        System.out.println(s1 == s2); // true/false
        System.out.println(s1);
        System.out.println(s2);

        es.shutdown();
    }
}
