package com.ztest;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;

/**
 * 科学家就餐问题,每个人左手边有个筷子围成圆, 必须拿到两个筷子才能吃饭,不能出现死锁
 * scientistbq满了,不在创建线程, 释放了之后,在创建线程,要使用消息队列来实现
 *
 * 如果设置A的时候要判断是否B可用,如果不可用(怎么判断,获取的时候等待), 不能设置A
 * AB都有,
 * NieSu 2018/8/18
 */
public class ChopsticksTest
{

  /**
   * 放筷子的队列
   */
  private static final List<String> csList = new LinkedList<String>()
  {
    {
      add("1");
      add("2");
      add("3");
      add("4");
      add("5");
    }
  };

  /**
   * 放吃饭的人队列, 最多有两个人同时吃饭
   */
  private static BlockingQueue<Scientist> scientistbq = new ArrayBlockingQueue<Scientist>(2);

  private static AtomicInteger count=new AtomicInteger();

  public static void main(String[] args)
  {
    for (int i = 0; i < 5; i++)
    {
      Thread takeOne = new Thread(new Runnable()
      {
        @Override
        public void run()
        {
          System.out.println("take one");
          String s = csList.get(count.getAndIncrement());
          System.out.println("取出"+s);
          Scientist scientist = new Scientist();
          scientist.setA(s);


        }
      });

      takeOne.start();
    }

    CommonUtil.sleep(3_000);


  }



}


@Data
class Scientist
{

  private String a;
  private String b;
}
