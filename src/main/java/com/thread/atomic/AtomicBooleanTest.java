package com.thread.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class AtomicBooleanTest
{
    private static AtomicBoolean isHappened = new AtomicBoolean(false);

    public static int clientTotal = 50_000_000;

    public static int threadTotal = 200;

    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++)
        {
            executorService.execute(() ->
            {
                try
                {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }

        try
        {
            countDownLatch.await();
            log.info(isHappened + "");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    private static void test()
    {
        //是false就设置为true
        if (isHappened.compareAndSet(false, true))
        {
            //控制某有一段代码只执行一次
            log.info("execute");
        }

    }
}
