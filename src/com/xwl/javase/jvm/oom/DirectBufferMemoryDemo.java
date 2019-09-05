package com.xwl.javase.jvm.oom;

import java.nio.ByteBuffer;

/**
 * @author xwl
 * @date 2019-09-04 15:46
 * @description
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        // 默认：配置的maxDirectMemory:1794.0MB 物理内存的1/4
        System.out.println("配置的maxDirectMemory:" + (sun.misc.VM.maxDirectMemory() / (double)1024 / 1024) + "MB");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 配置 -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
