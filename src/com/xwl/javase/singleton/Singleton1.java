package com.xwl.javase.singleton;

/**
 * 饿汉式：直接创建对象，不存在线程安全问题
 * 方式一：直接实例化饿汉式（简洁直观）
 * 	在类初始化时直接创建实例对象，不管你是否需要这个对象都会创建
 *
 *注意事项：
 * （1）构造器私有化
 * （2）自行创建，并且用静态变量保存
 * （3）向外提供这个实例
 * （4）强调这是一个单例，我们可以用final修改
 *
 * 总结：如果是饿汉式，枚举类形式最简单 即：Singleton2
 * 如果是懒汉式，静态内部内形式最简单 即：Singleton6
 */
public class Singleton1 {
	public static final Singleton1 INSTANCE = new Singleton1();
	// 构造器私有
	private Singleton1(){}

	public static void main(String[] args) {
		Singleton1 s = Singleton1.INSTANCE;
		System.out.println(s); // com.xwl.javase.singleton.Singleton1@1540e19d
	}
}
