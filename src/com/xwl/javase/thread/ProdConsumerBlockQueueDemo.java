package com.xwl.javase.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {
    /**
     * 默认开启，进行生产+消费的交互
     * 生产一个消费一个，必须保证线程可见性
     */
    private volatile boolean flag = true;
    /**
     * 默认值是0
     */
    private AtomicInteger atomicInteger = new AtomicInteger();

    private BlockingQueue<String> blockingQueue = null;

    /**
     * 构造方法
     * 传接口，不要传具体的类！！！，高手与菜鸟的区别
     *
     * @param blockingQueue 阻塞队列，接口！！
     */
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    /**
     * 生产
     *
     * @throws Exception
     */
    public void myProd() throws Exception {
        String data = null;
        boolean returnValue;
        while (flag) { // 只要涉及到多线程交互，一定是用while判断，不能用if
            data = atomicInteger.incrementAndGet() + "";
            // 使用offer方法向队列插入数据，当阻塞队列满时，队列会阻塞生产者线程一段时间，超过时间后生产者线程就会退出
            returnValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (returnValue) { // 使用offer方法返回值为true，则表示插入队列成功，false表示失败
                System.out.println(Thread.currentThread().getName() + "\t 插入队列数据" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列数据" + data + "失败");
            }
            // 1秒钟生产一个
            TimeUnit.SECONDS.sleep(1);
        }
        // flag = false;表示停止生产，队列满了
        System.out.println(Thread.currentThread().getName() + "\t 停止生产，flag = false");
    }

    /**
     * 消费
     *
     * @throws Exception
     */
    public void myConsumer() throws Exception {
        String result = null;
        while (flag) {
            // 使用poll方法取数据数据，当阻塞队列满时，队列会阻塞生产者线程一段时间，超过时间后生产者线程就会退出
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || "".equalsIgnoreCase(result)) {
                flag = false;
                System.out.println(Thread.currentThread().getName() + "\t" + "超过2m没有取到数据，消费退出");
                System.out.println();
                System.out.println();
                return; // 一定要加return
            }
            System.out.println(Thread.currentThread().getName() + "消费队列" + result + "成功");
        }
    }

    /**
     * 停止
     * @throws Exception
     */
    public void stop() throws Exception {
        flag = false;
    }
}

/**
 * 生产者消费者==>阻塞队列版
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 **/
public class ProdConsumerBlockQueueDemo {
    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
            System.out.println();
            System.out.println();
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("5秒钟时间到,停止活动");
        myResource.stop();
    }
}
 
