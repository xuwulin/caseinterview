package com.xwl.javase.jvm;

/**
 * @author xwl
 * @date 2019-09-04 13:57
 * @description
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object(); // 这样定义的默认就是强引用
        Object obj2 = obj1; // obj2引用赋值，强引用
        obj1 = null; // 置空,会被垃圾回收器回收
        System.gc();
        // obj2不会被垃圾回收器回收
        System.out.println(obj2); // java.lang.Object@5acf9800
    }
}
