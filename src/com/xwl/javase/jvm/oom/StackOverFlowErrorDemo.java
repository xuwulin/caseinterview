package com.xwl.javase.jvm.oom;

/**
 * @author xwl
 * @date 2019-09-04 15:02
 * @description
 * 栈管运行：大小默认在512k-1024k
 *
 * 导致原因：深度方法调用，导致栈内存不够用
 */
public class StackOverFlowErrorDemo {
    public static void main(String[] args) {
        stackOverFlowError();
    }

    private static void stackOverFlowError() {
        stackOverFlowError();
    }
}
