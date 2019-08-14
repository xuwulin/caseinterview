package com.xwl.javase.singleton;

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
}
