package com.xwl.javase.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全的问题
 */
public class ContainerNotSafeDemo {
    /**
     * 笔记
     * 写时复制 copyOnWrite 容器即写时复制的容器 往容器添加元素的时候,不直接往当前容器object[]添加,而是先将当前容器object[]进行
     * copy 复制出一个新的object[] newElements 然后向新容器object[] newElements 里面添加元素 添加元素后,
     * 再将原容器的引用指向新的容器 setArray(newElements);
     * 这样的好处是可以对copyOnWrite容器进行并发的读,而不需要加锁 因为当前容器不会添加任何容器.所以copyOnwrite容器也是一种
     * 读写分离的思想,读和写不同的容器.
     *
     * CopyOnWriteArrayList的add方法
     * public boolean add(E e) {
     *      final ReentrantLock lock = this.lock;
     *      lock.lock();
     *      try {
     *          Object[] elements = getArray();
     *          int len = elements.length;
     *          Object[] newElements = Arrays.copyOf(elements, len + 1);
     *          newElements[len] = e;
     *          setArray(newElements);
     *          return true;
     *      } finally {
     *      lock.unlock();
     *      }
     * }
     *
     * @param args
     */
    public static void main(String[] args) {
//        listNotSafe();

//        setNotSafe();

        mapNotSafe();
    }

    /**
     * HashMap线程不安全
     *
     * 1.故障现象
     *  java.util.ConcurrentModificationException：并发修改异常
     * 2.导致原因
     *    并发争抢修改导致
     *    参考花名册签名情况。一个人正在写入，另一个人过来抢夺，导致数据不一致，并发修改异常。
     * 3.解决方案
     *  3.1 new Vector<>()
     *  3.2 Collections.synchronizedMap(new HashSet<>());
     *  3.3 new ConcurrentHashMap<>(); // java.util.concurrent.CopyOnWriteArraySet;包
     *
     * 4.优化建议
     */
    public static void mapNotSafe() {
//        Map<String, String> map = new HashMap<>(); // 在高并发下回出现并发修改异常
        Map<String, Object> map = new ConcurrentHashMap<>(); // 建议使用ConcurrentHashMap解决高并发下的问题
        for (int i = 1; i <= 300; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(1, 8));
                System.out.println(map); // 可能出现异常：java.util.ConcurrentModificationException
            }, String.valueOf(i)).start();
        }
    }

    /**
     * HashSet线程不安全
     *
     * 1.故障现象
     *  java.util.ConcurrentModificationException：并发修改异常
     * 2.导致原因
     *    并发争抢修改导致
     *    参考花名册签名情况。一个人正在写入，另一个人过来抢夺，导致数据不一致，并发修改异常。
     * 3.解决方案
     *  3.1 new Vector<>()
     *  3.2 Collections.synchronizedSet(new HashSet<>());
     *  3.3 new CopyOnWriteArraySet<>(); // java.util.concurrent.CopyOnWriteArraySet;包
     *
     * 4.优化建议
     */
    public static void setNotSafe() {
//        Set<String> set = new HashSet<>(); // 在高并发下回出现并发修改异常
        Set<String> set = new CopyOnWriteArraySet<>(); // 建议使用CopyOnWriteArraySet解决高并发下的问题
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(1, 8));
                System.out.println(set); // 每次运行结果可能不一致，并且可能出现异常：java.util.ConcurrentModificationException
            }, String.valueOf(i)).start();
        }

        /**
         * 问题：HashSet的底层是什么？
         * 答：HashMap
         * 问题：既然HashSet的底层是HashMap，那为什么HashSet执行add()方法添加方法时，只能穿一个元素，但HashMap却是key value键值对呢？
         * 答：HashSet的add()方法调用的是HashMap的put()方法，而put()方法中的key就是add()方法中的元素,value是一个 Object PRESENT = new Object()常量
         * 即，所有的value都是一样的
         */
//        new HashSet<>();
    }


    /**
     * ArrayList线程不安全
     *
     * 1.故障现象
     *  java.util.ConcurrentModificationException：并发修改异常
     * 2.导致原因
     *    并发争抢修改导致
     *    参考花名册签名情况。一个人正在写入，另一个人过来抢夺，导致数据不一致，并发修改异常。
     * 3.解决方案
     *  3.1 new Vector<>()
     *  3.2 Collections.synchronizedList(new ArrayList<>());
     *  3.3 new CopyOnWriteArrayList<>(); // java.util.concurrent.CopyOnWriteArrayList;包下的
     *
     * 4.优化建议
     */
    public static void listNotSafe() {
//        List<String> list = new ArrayList<>(); // 在高并发下回出现并发修改异常
        List<String> list = new CopyOnWriteArrayList<>(); // 建议使用CopyOnWriteArrayList解决高并发下的问题
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(1, 8));
                System.out.println(list); // 每次运行结果可能不一致，并且可能出现异常：java.util.ConcurrentModificationException
            }, String.valueOf(i)).start();
        }

    }
}
