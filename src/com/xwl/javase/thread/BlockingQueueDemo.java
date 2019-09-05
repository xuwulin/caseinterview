package com.xwl.javase.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author xwl
 * @date 2019-09-03 0:25
 * @description
 * ArrayBlockingQueue: 由数组结构组成的有界阻塞队列，此队列按照FIFO(先进先出)原则对元素进行排序.
 * LinkedBlockingDeque: 由链表结构组成的有界(但大小默认值Integer>MAX_VALUE)阻塞队列，此队列按照FIFO(先进先出)原则对元素进行排序，吞吐量通常要高于ArrayBlockingQueue:
 * SynchronousQueue:不存储元素的阻塞队列,也即是单个元素的队列，每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高.
 *
 * 1、队列
 *
 * 2、阻塞队列
 *  2.1 阻塞队列有没有好的一面
 *  2.2 不得不阻塞，你如何管理
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
//        List list = new ArrayList(); // ArrayList默认大小是10
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3); // ArrayBlockingQueue默认大小是
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        blockingQueue.remove(); // 首先移除a,队列先进先出
        blockingQueue.remove("b"); // 指定移除元素
    }
}
