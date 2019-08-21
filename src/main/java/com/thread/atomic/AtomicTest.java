package com.thread.atomic;

import com.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * atomic类 底层时cas,循环比较,竞争激烈的时候,可能出现死循环
 * AtomicLong
 * LongAdder  JDK 1.8出现 和AtomicLong使用一样,高并发优先使用LongAddr,线程竟争低的时候使用AtomicLong
 */
@Slf4j
@ThreadSafe
public class AtomicTest
{

    public static int clientTotal = 500;
    public static int threadTotal = 20;
    //  public static AtomicInteger count=new AtomicInteger(0);

    //public static AtomicLong count =new AtomicLong(0);
    public static LongAdder count = new LongAdder();

    public static void main(String[] args) throws Exception
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++)
        {
            executorService.execute(() ->
            {
                try
                {
                    semaphore.acquire();
                    //打印这句话不是线程安全的
                    //  log.info("count :{}", count);

                    add();
                    semaphore.release();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                countDownLatch.countDown();
            });

        }

        countDownLatch.await();

        executorService.shutdown();
        log.info("count :{}", count);
    }

    private static void add()
    {
        //线程安全
        // count.incrementAndGet();

        count.increment();
    }
}
