package com.httpleak;

import java.util.List;

/**
 * @author NieSu
 */
public class ReplayApplication
{

//  public static void main(String[] args) throws InterruptedException
//  {
//
//    //创建有内存泄露的回放客户端
//    ReplayWithProblem replay1 = new ReplayWithProblem();
//
//    //加载一万条请求数据放入缓存
//    List<HttpUriRequest> cache1 = replay1.loadMockRequest(10_000);
//
//    //开始循环回放
//    replay1.start(cache1);
//
//  }

  public static void main(String[] args) throws InterruptedException
  {

    ReplayWithoutProblem replay2 = new ReplayWithoutProblem();
    List<String> cache2 = replay2.loadMockRequest(10000);
    replay2.start(cache2);

  }
}