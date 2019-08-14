package com.xwl.javase.singleton.test;

import com.xwl.javase.singleton.Singleton2;

import java.sql.SQLOutput;

/**
 * @author xwl
 * @date 2019-08-13 0:02
 * @description 测试Singleton2,怎样获取枚举类型的单例模式的实例
 */
public class TestSingleton2 {
    public static void main(String[] args) {
        Singleton2 singleton2 = Singleton2.INSTANCE;
        // 枚举中已经帮我们重写了toString()方法，返回的是常量对象的名字：INSTANCE
        System.out.println(singleton2); // INSTANCE
    }
}
