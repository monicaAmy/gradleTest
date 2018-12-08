package com.httpleak;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;

/**
 * FutureCallback 该回调匿名类在 http 回调结束后，引用关系就没了，在下一次 GC 理应被回收才对。 通过对 httpasyncclient
 * 发送请求的源码进行跟踪发现，其内部实现是将回调类塞入到了http的请求类中， 而请求类是放在在缓存队列中，所以导致回调类的引用关系没有解除，大量的回调类晋升到了old区，最终导致 Full GC
 * 产生。
 *
 * @author
 */
public class ReplayWithProblem
{

  public List<HttpUriRequest> loadMockRequest(int n)
  {

    List<HttpUriRequest> cache = new ArrayList<HttpUriRequest>(n);
    for (int i = 0; i < n; i++)
    {
      //这里需要修改
      HttpGet request = new HttpGet("http://www.baidu.com?a=" + i);
      cache.add(request);
    }
    return cache;

  }

  public void start(List<HttpUriRequest> cache) throws InterruptedException
  {

    HttpAsyncClient httpClient = new HttpAsyncClient();
    int i = 0;

    while (true)
    {
      //这里需要修改
      final HttpUriRequest request = cache.get(i % cache.size());
      httpClient.execute(request, new FutureCallback<HttpResponse>()
      {
        @Override
        public void completed(final HttpResponse response)
        {
          System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
        }

        @Override
        public void failed(final Exception ex)
        {
          System.out.println(request.getRequestLine() + "->" + ex);
        }

        @Override
        public void cancelled()
        {
          System.out.println(request.getRequestLine() + " cancelled");
        }

      });
      i++;
      Thread.sleep(1000);
    }
  }

}