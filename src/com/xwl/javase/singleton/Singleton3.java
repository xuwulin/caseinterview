package com.xwl.javase.singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * 饿汉式：直接创建对象，不存在线程安全问题
 * 方式三：静态代码块饿汉式（适合复杂实例化）
 */
public class Singleton3 {
	public static final Singleton3 INSTANCE;
	private String info;

	// 静态代码块
	static{
		try {
			// 读取配置文件中的属性
			Properties pro = new Properties();
			// 通过类加载器加载
			// Singleton3.class.getClassLoader()：当前类的类加载器
			// getResourceAsStream("")：加载类路径下(src下配置文件：single.properties)的资源
			pro.load(Singleton3.class.getClassLoader().getResourceAsStream("single.properties"));
			// pro.getProperty("info")读取配置文件中的info属性的值
			INSTANCE = new Singleton3(pro.getProperty("info"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// 构造器私有化
	private Singleton3(String info){
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Singleton3 [info=" + info + "]";
	}
	
}
