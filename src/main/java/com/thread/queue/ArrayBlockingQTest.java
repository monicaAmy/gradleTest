package com.thread.queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue 定长,大任务量的时候做限定
 * LinkedBlockingQueue 无界,可以承载任务量的时候
 * SynchronousQueue 不能添加元素,任务量极少,可以直接处理,不需要加载到队列中
 * NieSu 2018/4/23
 */
public class ArrayBlockingQTest
{

  public static void main(String[] args)throws Exception
  {
//    ArrayBlockingQueue 基于数组的阻塞队列,长度需要指定的定长数组
// 可以先进先出或者先进后出,不能读写分离
    ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(5);
    arrayBlockingQueue.offer("a",2, TimeUnit.SECONDS);
    arrayBlockingQueue.offer("b",2, TimeUnit.SECONDS);
    arrayBlockingQueue.offer("c",2, TimeUnit.SECONDS);
    arrayBlockingQueue.offer("d",2, TimeUnit.SECONDS);
    arrayBlockingQueue.offer("e",2, TimeUnit.SECONDS);
    //加不进去
    arrayBlockingQueue.offer("f",2, TimeUnit.SECONDS);
    //System.out.println(arrayBlockingQueue.peek());
    for (Iterator iterator = arrayBlockingQueue.iterator(); iterator.hasNext();)
    {
      Object next = iterator.next();
      System.out.println((String) next);
    }

    //无界阻塞队列,读写分离锁,能够处理高并发
    LinkedBlockingQueue<String> objects = new LinkedBlockingQueue<>();
    objects.offer("a");
    objects.offer("b");
    objects.offer("c");
    objects.offer("d");
    objects.offer("e");

    List<String> strings = new ArrayList<String>();
    System.out.println(objects.drainTo(strings, 3));

    strings.forEach(x-> System.out.println("----"+x));

    //传入对象实现Comparable接口,利用Compator来决定优先级,内部是公平锁,无界队列
    PriorityBlockingQueue<Object> objects1 = new PriorityBlockingQueue<>();
    //传入对象必须实现Delayed接口,到了指定的延时时间才能从队列中获取元素
    //没有大小限制的队列,对缓存数据进行删除,处理超时任务,空闲链接关闭等
    DelayQueue delayQueue = new DelayQueue();
    //没有缓冲的队列
    SynchronousQueue<Object> objects2 = new SynchronousQueue<>();
    //不能添加元素,报错
    objects2.add("ddd");
  }
}
