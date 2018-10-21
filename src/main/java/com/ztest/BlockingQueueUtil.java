package com.ztest;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 抛异常：如果试图的操作无法立即执行，抛一个异常。 特定值：如果试图的操作无法立即执行，返回一个特定的值(常常是 true / false)。
 * 阻塞：如果试图的操作无法立即执行，该方法调用将会发生阻塞，直到能够执行。 超时：如果试图的操作无法立即执行，该方法调用将会发生阻塞，直到能够执行，但等待时间不会超过给定值。返回一个特定值以告知该操作是否成功(典型的是true
 * / false)。
 *
 *
 * NieSu 2018/8/13
 */
public class BlockingQueueUtil
{

  private final LinkedList<Object> bqlist = new LinkedList<Object>();
  private final int minSize = 0;
  private final int maxSize;
  private final Object lock = new Object();
  private AtomicInteger count = new AtomicInteger(0);

  //构造方法，构造容器最大长度
  public BlockingQueueUtil(int size)
  {
    this.maxSize = size;
  }


  public static void main(String[] args)
  {
    BlockingQueueUtil blockingQueueUtil = new BlockingQueueUtil(3);

    for (int i = 0; i < 1_000_000; i++)
    {
      System.out.println("-------------------" + blockingQueueUtil.size());

      Thread thread = new Thread(new Runnable()
      {
        @Override
        public void run()
        {
          blockingQueueUtil.add("a"+Thread.currentThread().getName());
          blockingQueueUtil.add("a"+Thread.currentThread().getName());
        }
      });
      thread.start();
      Thread thread2 = new Thread(new Runnable()
      {
        @Override
        public void run()
        {
          blockingQueueUtil.add("b"+Thread.currentThread().getName());
          blockingQueueUtil.add("b"+Thread.currentThread().getName());
        }
      });
      thread2.start();

     // CommonUtil.sleep(1000);
      System.out.println("当前容器的大小：" + blockingQueueUtil.size());
      Thread thread1 = new Thread(new Runnable()
      {
        @Override
        public void run()
        {
          blockingQueueUtil.del();
        }
      });
      thread1.start();
    }




  }

  /**
   * 添加一个对象，如果队列满了，则阻塞
   */

  public boolean add(Object o)
  {
    synchronized (lock)
    {
      while (bqlist.size() == maxSize)
     // while (count.get() == maxSize)
      {
        try
        {
          lock.wait();
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
      bqlist.add(o);
      //count.incrementAndGet();
      System.out.println("添加" + o);
      lock.notify();
    }
    return true;
  }

  /**
   * 取出一个元素，如果队列为空，则阻塞
   */
  public Object del()
  {
    Object o = null;
    synchronized (lock)
    {
      while (bqlist.size() == minSize)
     // while (count.get() == minSize)
      {
        try
        {
          lock.wait();
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
      o = bqlist.removeFirst();
     // count.decrementAndGet();
      System.out.println("取出" + o);
      lock.notify();
    }
    return o;
  }

  /**
   * 检查
   */
  public boolean contain(Object o)
  {
    return bqlist.contains(o);
  }

  public int size()
  {
    //return count.get();
    return bqlist.size();
  }

}
