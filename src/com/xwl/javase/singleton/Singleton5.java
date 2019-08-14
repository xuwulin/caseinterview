package com.xwl.javase.singleton;

/**
 * 懒汉式：延迟创建这个实例对象
 * 	方式二：线程安全（适用于多线程）
 *
 * (1)构造器私有化
 * (2)用一个静态变量保存这个唯一的实例
 * (3)提供一个静态方法，获取这个实例对象
 */
public class Singleton5 {
    private static Singleton5 instance;

    private Singleton5() {
    }

    public static Singleton5 getInstance() {
    	// 方式一：效率差
		/*synchronized (Singleton5.class) { // 静态方法的同步监视器为 当前类.class
			if (instance == null) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				instance = new Singleton5();
			}
		}*/

    	// 方式二：效率高
        if (instance == null) {
            synchronized (Singleton5.class) { // 静态方法的同步监视器为 当前类.class
                if (instance == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
