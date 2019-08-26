package com.publishobj;

import com.annotation.NotThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@NotThreadSafe
public class UnsafePublish
{
    @Getter
    private String[] states = {"zz", "xx", "cc"};

    public static void main(String[] args)
    {
        UnsafePublish unsafePublish = new UnsafePublish();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() ->
        {

            String[] states = unsafePublish.getStates();
            log.info("test01 {}", Arrays.toString(states));

            states[0] = "aa";
            log.info("test01  ---  {}", Arrays.toString(unsafePublish.getStates()));
        });

        for (int i = 0; i < 10; i++)
        {
            executorService.execute(() ->
            {
                log.info("{}", Arrays.toString(unsafePublish.getStates()));
            });

        }
        executorService.shutdown();
    }
}
