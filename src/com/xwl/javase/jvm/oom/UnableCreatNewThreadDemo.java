package com.xwl.javase.jvm.oom;

/**
 * @author xwl
 * @date 2019-09-04 16:03
 * @description
 */
public class UnableCreatNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 0; ; i++) { // 没有跳出for循环的的条件，一直循环
            System.out.println("*************i = " + i);
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "" + i).start();
        }
    }
}
