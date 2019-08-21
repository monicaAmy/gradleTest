package com.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 控制同时并发线程数
 * countDownLatch 保证线程执行
 */
@Slf4j
@NotThreadSafe
public class ConcurrencyTest
{
    public static int clientTotal = 50;
    public static int threadTotal = 20;
    //静态类型变量跟随类的定义存放在堆上,为线程共享
    public static int count = 0;

    public static void main(String[] args)
    {
        //返回一个根据实际情况调整线程个数的线程池,不限制最大线程数量,有空闲线程则执行,没有不创建线程,并且每一个空闲线程会在60秒后自动回收
        ExecutorService executorService = Executors.newCachedThreadPool();
        //每次执行200个,超过任务数要等到释放的时候才执行
        final Semaphore semaphore = new Semaphore(threadTotal);//2
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);//50

        //创建10个线程,交给线程池去执行
        for (int i = 0; i < clientTotal; i++)
        {
            executorService.execute(() ->
            {
                Thread current = Thread.currentThread();
                //  System.out.println(current.getPriority());
                System.out.println(current.getName());
                // System.out.println(current.activeCount());
                //      System.out.println(current.getId());
//                System.out.println(current.getThreadGroup());
//                System.out.println(current.getStackTrace());
//                System.out.println(current.hashCode());
//                System.out.println(current.toString());

                try
                {
                    semaphore.acquire();
                    log.info("count :{}", count);
                    add();
                    //不释放的话,只能执行两次
                    semaphore.release();

                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });

        }
        try
        {
            //clientTotal=0时才能唤起await方法
            countDownLatch.await();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        executorService.shutdown();
        log.info("count :{}", count);
    }

    private static void add()
    {
        count++;
    }
}
