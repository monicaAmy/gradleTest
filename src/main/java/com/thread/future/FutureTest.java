package com.thread.future;

/**
 * FutureTask:用于异步获取执行结果或者取消执行任务的场景 基于CAS 避免锁的引用
 * NieSu 2018/5/1
 */
public class FutureTest
{

    public static void main(String[] args)
    {
        FutureClient fClient = new FutureClient();

        Data data = fClient.request("hello,world");

        System.out.println("请求发送成功...");
        System.out.println("干其他的事情...");

        String result = data.getRequest();

        System.out.println(result);
    }
}


