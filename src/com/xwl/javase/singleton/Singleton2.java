package com.xwl.javase.singleton;

/**
 * 饿汉式：直接创建对象，不存在线程安全问题
 * 方式二：枚举类（最简洁）
 *
 * 枚举类型：表示该类型的对象是有限的几个
 * 我们可以限定为一个，就成了单例
 */
public enum Singleton2 {
	INSTANCE
}
