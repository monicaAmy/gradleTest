package com.ztest;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import org.junit.Test;

/**
 * NieSu 2018/8/12
 */
public class Fun
{

  public static void main(String[] args) throws Exception
  {
    Map<Thread, StackTraceElement[]> maps = Thread.getAllStackTraces();

    ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
    while (threadGroup.getParent() != null)
    {
      threadGroup = threadGroup.getParent();
    }
    int totalThread = threadGroup.activeCount();
    System.out.println(totalThread);

//    System.out.println(write.getName() + " 存活:" + write.isAlive() + " 状态:" + write.getState());
//    System.out.println("------------------");
//    System.out.println(read.getName() + " 存活:" + read.isAlive() + " 状态:" + read.getState());
  }

  public static int getRandom( )
  {
    try
    {
      SecureRandom secureRandom = new SecureRandom();
      SecureRandom secureRandom3 = SecureRandom.getInstance("SHA1PRNG");
      SecureRandom secureRandom2 = SecureRandom.getInstance("SHA1PRNG", "SUN");
      int i = secureRandom2.nextInt();
      return i;
    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    catch (NoSuchProviderException e)
    {
      e.printStackTrace();
    }
    return -1;
  }

  @Test
  public void fn(){
    ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
    //使用add会报错 queue full, 使用offer不会
    arrayBlockingQueue.offer("a1");
    arrayBlockingQueue.offer("a2");
    arrayBlockingQueue.offer("a3");
    arrayBlockingQueue.offer("a4");
    arrayBlockingQueue.offer("a5");
    arrayBlockingQueue.offer("a6");

    System.out.println(arrayBlockingQueue.size());

    Object poll = arrayBlockingQueue.poll();
    System.out.println("删除"+poll);

    System.out.println(arrayBlockingQueue.size());

  }


}

