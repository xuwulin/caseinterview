package com.xwl.javase.program.step;

/**
 * @author xwl
 * @date 2019-08-13 14:49
 * @description 编程题：有n步台阶，一次只能上1步或2步，共有多少种走法？
 * 方式一：递归
 *
 */
public class TestStep1 {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        long start = System.currentTimeMillis();
        System.out.println(f(40));//165580141
        long end = System.currentTimeMillis();
        System.out.println(end - start);//265ms
    }

    // 实现f(n)：求n步台阶，一共有几种走法
    public static int f(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return f(n - 2) + f(n - 1);
    }
}
