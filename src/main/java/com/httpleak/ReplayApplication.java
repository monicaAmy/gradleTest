package com.httpleak;

import java.util.List;

/**
 *GC后回收的力度越来越小，则说明很有可能存在内存泄漏，这时需要开启该视图的Memory Views视图的Recorded Objects子视图
 *
 * 通过占用时间的不同，找出系统里最耗时的类方法进行调优解决问题
 *
 * Java虚拟机工作原理 - best.lei - 博客园.html
 * @author
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