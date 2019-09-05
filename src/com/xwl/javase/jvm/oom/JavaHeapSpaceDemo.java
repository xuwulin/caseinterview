package com.xwl.javase.jvm.oom;

import java.util.Random;

/**
 * @author xwl
 * @date 2019-09-04 15:11
 * @description
 *
 * JVM参数：-Xms10m -Xmx10m
 *
 * 导致原因：对象太大或者太多，堆空间不够用
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        // 或者直接来一个大对象
        byte[] bytes = new byte[80 * 1024 * 1024];

//        String str = "hello";
//        while (true) {
//            // 使用 += 会产生很多很多的对象
//            str += str + new Random().nextInt(11111) + new Random().nextInt(22222);
//            str.intern();
//        }


    }
}
