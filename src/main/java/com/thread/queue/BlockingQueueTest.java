package com.thread.queue;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞队列
 * put 队列空间满了,阻塞,有空间的时候,添加元素
 * take 取首位元素,没有元素,阻塞等待,直到有一个元素
 * NieSu 2018/4/22
 */
public class BlockingQueueTest
{
  private LinkedList<Object> list=new LinkedList<Object>();

  private AtomicInteger count= new AtomicInteger(0);

  private final int minSize=0;

  private final int maxSize;

  public  BlockingQueueTest(int size)
  {
    this.maxSize=size;
  }

  private final Object lock=new Object();

  public void put(Object obj)
  {
    synchronized (lock)
    {
      while(maxSize==count.get())
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
      list.add(obj);
      count.incrementAndGet();
      System.out.println("新加入元素为: "+obj);
      lock.notify();
    }
  }

  public Object take()
  {
    Object object=null;
    synchronized (lock)
    {
      while (count.get()==this.minSize)
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
      object=list.removeFirst();
      count.decrementAndGet();
      System.out.println("去除元素: "+object);
      lock.notify();
    }
    return object;
  }
  public int getSize()
  {
    return count.get();
  }

  public static void main(String[] args)
  {
    BlockingQueueTest blockingQueueTest = new BlockingQueueTest(5);
    blockingQueueTest.put("1");
    blockingQueueTest.put("2");
    blockingQueueTest.put("3");
    blockingQueueTest.put("4");
    blockingQueueTest.put("5");

    Thread thread = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        blockingQueueTest.put("7");
        blockingQueueTest.put("8");
      }
    }, "t1");

    Thread thread1 = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        blockingQueueTest.take();
        blockingQueueTest.take();
      }
    }, "t2");

    thread.start();
    thread1.start();
    blockingQueueTest.take();
  }

}
