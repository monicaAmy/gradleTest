package com.thread.single;

/**
 * NieSu 2018/4/22 单例在多线程的最佳实践
 */
public class StaticTest
{

  public static Singletion getInstance()
  {
    return Singletion.singletion;
  }

  public static class Singletion
  {
    private static Singletion singletion = new Singletion();
  }

  public static void main(String[] args)
  {
    for (int i = 0; i < 100; i++)
    {

      Singletion instance = StaticTest.getInstance();
      //全都是同一个对象
      System.out.println(instance);
    }
  }
}
