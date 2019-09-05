package com.xwl.javase.jvm;

import java.util.concurrent.TimeUnit;

/**
 * @author xwl
 * @date 2019-09-03 22:59
 * @description
 */
public class HelloGC {
    public static void main(String[] args) {
//        System.out.println("*********HelloGC");
        byte[] bytes = new byte[50 * 1024 * 1024];
//        try {
//            TimeUnit.SECONDS.sleep(100000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        // 返回java虚拟机中的内存总量
//        long totalMemory = Runtime.getRuntime().totalMemory();
//        // 返回java虚拟机中视图使用的最大内存
//        long maxMemory = Runtime.getRuntime().maxMemory();
//        // totalMemory(-Xms) = 128974848(字节)、123.0MB
//        System.out.println("totalMemory(-Xms) = " + totalMemory + "(字节)、" + (totalMemory / (double)1024 / 1024) + "MB");
//        // maxMemory(-Xms) = 1881145344(字节)、1794.0MB
//        System.out.println("maxMemory(-Xms) = " + maxMemory + "(字节)、" + (maxMemory / (double)1024 / 1024) + "MB");
    }
}
