package com.xwl.javase.singleton.test;

import com.xwl.javase.singleton.Singleton3;

/**
 * @author xwl
 * @date 2019-08-13 0:17
 * @description
 */
public class TestSingleton3 {
    public static void main(String[] args) {
        Singleton3 singleton3 = Singleton3.INSTANCE;
        System.out.println(singleton3);
    }
}
