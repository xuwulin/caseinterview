package com.xwl.javase.jvm;

import java.lang.ref.WeakReference;

/**
 * @author xwl
 * @date 2019-09-04 14:23
 * @description
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1); // java.lang.Object@5acf9800
        System.out.println(weakReference.get()); // java.lang.Object@5acf9800

        o1 = null;
        System.gc();
        System.out.println("===============");

        System.out.println(o1); // null
        System.out.println(weakReference.get()); // null
    }
}
