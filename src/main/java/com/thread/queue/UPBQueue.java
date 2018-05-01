package com.thread.queue;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * NieSu 2018/5/1
 */
public class UPBQueue
{

  public static void main(String[] args)
  {
    try
    {
      PriorityBlockingQueue<PBTest> pbQueue = new PriorityBlockingQueue<>();
      PBTest pbTest = new PBTest();
      pbTest.setId(10);
      pbTest.setName("pbTest");

      PBTest pbTest1 = new PBTest();
      pbTest1.setId(9);
      pbTest1.setName("pbTest1");

      PBTest pbTest2 = new PBTest();
      pbTest2.setId(2);
      pbTest2.setName("pbTest2");
      //add方法不比较
      pbQueue.add(pbTest);
      pbQueue.add(pbTest1);
      pbQueue.add(pbTest2);

      System.out.println(pbQueue);
      //take方法拿出来的才有排序,take之后被删除掉了
      PBTest take = pbQueue.take();
      System.out.println(take);
      PBTest take1 = pbQueue.take();
      System.out.println(take1);
      System.out.println("take ....");

      //迭代输出的没有排序
      for (Iterator<PBTest> iterator = pbQueue.iterator(); iterator.hasNext(); )
      {
        PBTest next = iterator.next();
        System.out.println(next);
      }
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }

  }

}
