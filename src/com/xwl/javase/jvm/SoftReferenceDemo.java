package com.xwl.javase.jvm;

import java.lang.ref.SoftReference;

/**
 * @author xwl
 * @date 2019-09-04 14:12
 * @description
 */
public class SoftReferenceDemo {
    /**
     * 内存够用的时候就保留
     */
    public static void softRef_MemoryEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1); // java.lang.Object@5acf9800
        System.out.println(softReference.get()); // java.lang.Object@5acf9800

        o1 = null; // null
        System.gc();

        System.out.println(o1);
        System.out.println(softReference.get()); // java.lang.Object@5acf9800
    }

    /**
     * JVM配置，故意产生大对象并配置小的内存，让它内存不够用导致OOM，看软引用的垃圾回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_MemoryNotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1); // java.lang.Object@5acf9800
        System.out.println(softReference.get()); // java.lang.Object@5acf9800

        o1 = null;

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1); // null
            System.out.println(softReference.get()); // null
        }

    }

    public static void main(String[] args) {
//        softRef_MemoryEnough();
        softRef_MemoryNotEnough();
    }
}
