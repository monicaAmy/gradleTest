package com.thread.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 很难测试出问题
 */
@Slf4j
public class AtomicBooleanTest01
{
    private static Boolean isHappened = false;

    public static int clientTotal = 50_000_000;

    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < clientTotal; i++)
        {
            executorService.execute(() ->
            {
                try
                {
                    test();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            });
        }

    }

    private static void test()
    {
        if (isHappened == false)
        {
            isHappened = true;
            log.info("execute");
        }
    }
}
