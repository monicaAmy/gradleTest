package com.thread.future;

/**
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
