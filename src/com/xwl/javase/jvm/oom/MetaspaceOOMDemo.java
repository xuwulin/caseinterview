package com.xwl.javase.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xwl
 * @date 2019-09-04 16:20
 * @description
 * JVM参数配置：-XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 */
public class MetaspaceOOMDemo {
    static class OOMTest {

    }

    public static void main(String[] args) {
        int i = 0; // 模拟计数多少次后发生异常
        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }
        } catch (Exception e) {
            System.out.println("**********多少次后发生了异常： " + i);
            e.printStackTrace();
        }
    }
}
