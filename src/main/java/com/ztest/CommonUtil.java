package com.ztest;

/**
 * NieSu 2018/8/15
 */
public class CommonUtil
{

  public static void sleep(long millis)
  {
    try
    {
      Thread.sleep(millis);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }


}
