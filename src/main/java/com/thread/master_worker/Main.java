package com.thread.master_worker;

import java.util.Random;

/**
 * 分析过程：
 *
 * 同理由于Worker处理完成后将数据填充进Master的ConcurrentHashMap，那么它也得有一份ConcurrentHashMap的引用
 */
public class Main
{

  public static void main(String[] args)
  {
    //实际开发中多少个线程最好写成Runtime.getRuntime().availableProcessors()
    //创建10个线程,每个线程拥有同一个worker
    Master master = new Master(new Worker(), 10);
    Random random = new Random();
    //添加100个任务到队列中
    for (int i = 0; i <= 100; i++)
    {
      Task task = new Task();
      task.setId(i);
      task.setName("任务" + i);
      task.setPrice(random.nextInt(1000));
      master.submit(task);
    }
    //启动所有线程
    master.execute();
    long start = System.currentTimeMillis();
    while (true)
    {
      if (master.isComplete())
      {
        long end = System.currentTimeMillis() - start;
        int ret = master.getResult();
        System.out.println("计算结果:" + ret + ",执行耗时:" + end);
        break;
      }
    }
  }
}