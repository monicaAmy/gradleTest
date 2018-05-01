package com.thread.future;

public class FutureData implements Data
{

  private RealData realData;

  private boolean isReady = false;

  public synchronized String getRequest()
  {

    while (!isReady)
    {
      try
      {
        System.out.println("wait...");
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    return this.realData.getRequest();
  }

  public synchronized void setRealData(RealData realData)
  {
    if (isReady)
    {
      return;
    }
    this.realData = realData;
    isReady = true;
    System.out.println("notify...");
    notify();
  }
}
