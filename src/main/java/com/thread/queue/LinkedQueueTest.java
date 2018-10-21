package com.thread.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentLinkedQueue高并发场景下的队列
 * 通过无锁的方式,实现高并发状态下的高性能,性能好于BlockingQueue
 * 基于链接节点的无界线程安全队列,先进先出,不允许null元素
 * NieSu 2018/4/23
 */
public class LinkedQueueTest
{

  public static void main(String[] args)
  {
    ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
   //两个添加方法没有区别, offer queue满了在调用添加不会报错
    queue.add("ddd");
    queue.offer("hah");
    //取头并删除
    System.out.println(queue.poll());
    //取头
    System.out.println(queue.peek());

    System.out.println(queue.size());
  }
}
