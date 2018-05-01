package com.thread.futurec;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Main {

    public static void main(String[] args) throws Exception {
        Long start = System.currentTimeMillis();

        FutureTask<String> futureTask = new FutureTask<>(new RealData("hello,world"));
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(1);
        newFixedThreadPool.submit(futureTask);

        // 表示正在处理其他逻辑,或者业务
        Thread.sleep(1000);

        System.out.println("最后结果-->" + futureTask.get());

        Long end = System.currentTimeMillis();

        Long useTime = end - start;

        System.out.println("程序运行了-->" + useTime + "毫秒");
    }

}
