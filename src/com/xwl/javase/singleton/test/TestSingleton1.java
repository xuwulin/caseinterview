package com.xwl.javase.singleton.test;

import com.xwl.javase.singleton.Singleton1;

/**
 * @author xwl
 * @date 2019-08-13 0:07
 * @description 测试TestSingleton1
 */
public class TestSingleton1 {
    public static void main(String[] args) {
        Singleton1 s = Singleton1.INSTANCE;
        System.out.println(s); // com.xwl.javase.singleton.Singleton1@1540e19d
    }
}
