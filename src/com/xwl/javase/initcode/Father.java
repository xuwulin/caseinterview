package com.xwl.javase.initcode;

/**
 * 父类的初始化<clinit>：
 * （1）静态类变量显示赋值代码：j = method();
 * （2）父类的静态代码块
 *
 *  父类的实例化方法：
 * （1）super()（最前）
 * （2）i = test();
 * （3）父类的非静态代码块
 * （4）父类的无参构造（最后）
 *
 * 非静态方法前面其实有一个默认的对象this
 * this在构造器（或<init>）它表示的是正在创建的对象，因为这里是在创建Son对象，
 * 所以test()执行的是子类重写的代码（面向对象多态）
 *
 * 这里i=test()执行的是子类重写的test()方法，打印:(9)
 */
public class Father {
    private int i = test();
    private static int j = method();

    // 构造器
	Father() {
		System.out.print("(2)");
	}

    // 静态代码块
    static {
        System.out.print("(1)");
    }
    // 非静态代码块
    {
        System.out.print("(3)");
    }

    public int test() {
        System.out.print("(4)");
        return 1;
    }

    public static int method() {
        System.out.print("(5)");
        return 1;
    }
}