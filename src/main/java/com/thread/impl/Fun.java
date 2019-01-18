package com.thread.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * java.util.concurrent.Executor 接口 线程池顶级类 java.util.concurrent.Executors 类 一个是接口,一个是工具类 例如Array
 * Arrays java.util.concurrent.Executor 执行接口  接口execute方法没有实现 >java.util.concurrent.ExecutorService
 * 提交接口 submit 接口,方法没有实现 >java.util.concurrent.AbstractExecutorService 抽象类  把执行和提交的接口合并 实现submit(有坑)
 * 调用execute方法 >Java.uitl.concurrent.ThreadPoolExecutor 真正线程池类 java普通线程池,实现execute
 * >java.util.concurrent.ScheduledThreadPoolExecutor 定时任务 线程池
 *
 *
 * java.util.concurrent.Executor public ThreadPoolExecutor(int corePoolSize, 核心线程数 int
 * maximumPoolSize,线程总数 long keepAliveTime,保持时长 TimeUnit unit,市场单位 BlockingQueue<Runnable>
 * workQueue,队列类型 ThreadFactory threadFactory, 线程工厂 RejectedExecutionHandler handler)拒绝策略
 *
 * 面试题: java一共提供了多少线程池 2种 pool.submit() execute()有什么区别 submit有返回值 从AbstractExecutorService里面看
 * 底层实现都一样 都是调用Java.uitl.concurrent.ThreadPoolExecutor execute 方法
 *
 * 主线程调用execute方法→ 小于核心 addworker方法 Work run > runWorker 大于核心,offer到task里面(队列)
 * 队列也满了,上面都不满足,直接new一个非核心的线程 超过总线程数, 拒绝策略RejectedExecutionhandler
 *
 * java.util.concurrent.ThreadPoolExecutor.addWorker >java.lang.Thread.start
 * >java.util.concurrent.ThreadPoolExecutor.Worker.run AQS RUNABLE >java.util.concurrent.ThreadPoolExecutor.runWorker
 * >java.lang.Runable.run
 *
 *
 *
 *
 * NieSu 2019/1/15
 */
public class Fun
{

  public static void main(String[] args)
  {
    long start = System.currentTimeMillis();
    Random random = new Random();
    ArrayList<Integer> integers = new ArrayList<>();
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    for (int i = 0; i < 10000; i++)
    {
      executorService.submit(new Runnable()
      {
        @Override
        public void run()
        {
          integers.add(random.nextInt());
        }
      });
    }

    long end = System.currentTimeMillis();
    Date date = new Date(end);

    System.out.println(date);

    long time = end - start;
    System.out.println(time);
  }

}
