package com.xwl.javase.jvm;

/**
 * @author xwl
 * @date 2019-09-03 22:21
 * @description
 * 在java中，可作为GC Roots的对象有
 * 1.虚拟机栈（栈帧中的本地变量表）中引用的对象；
 * 2.方法区中的类静态属性引用的对象
 * 3.方法区中常量引用的对象
 * 4.本地方法栈中JNI（即一般说的Native方法）中引用的对象
 */
public class GCRootDemo {
    private byte[] byteArray = new byte[100 * 1024 * 1024];
        // 2.static修饰，静态的，只有一份，全部实例共用，被加载进了方法区，java8以前叫永久代，java8叫元空间
//    private static GCRootDemo2 t2;
    // 3.static final表示常量引用，且是强引用，不容易被回收掉
//    private static final GCRootDemo3 t3 = new GCRootDemo3(8);

    public static void m1() {
        // 1.方法是在栈中，局部变量t1也在栈中，指向堆中的对象
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
