package com.ztest;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Map;

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
}

