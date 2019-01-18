package com.thread.impl;

/**
 * JDK1.5 之前是Thread Runable 没有返回值 JDK1.5 之后有返回值,支持泛型,异步,线程池
 *
 * * @author  Arthur van Hoff * @see     java.lang.Thread * @see     java.util.concurrent.Callable *
 * @since JDK1.0 Runnable上面的注释找到 Callable包含泛型
 */
public class Runable01 implements Runnable
{

  @Override
  public void run()
  {
    System.out.println("线程执行了");
  }

  public static void main(String[] args)
  {
    //都可以执行线程
    new Runable01().run();
    System.out.println("------------------1------------------");
    new Thread(new Runable01()).start();
    System.out.println("------------------2--------------");
    new Thread(new Runable01()).run();
  }
}
