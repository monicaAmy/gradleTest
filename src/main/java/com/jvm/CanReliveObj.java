package com.jvm;

/**
 * Created by smyhvae on 2015/8/19.
 */
public class CanReliveObj
{
    public static CanReliveObj obj;

    //当执行GC时，会执行finalize方法，并且只会执行一次
    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
        System.out.println("CanReliveObj finalize called");
        obj = this;   //当执行GC时，会执行finalize方法，然后这一行代码的作用是将null的object复活一下，然后变成了可触及性
    }

    @Override
    public String toString()
    {
        return "I am CanReliveObj";
    }

    public static void main(String[] args) throws
            InterruptedException
    {
        obj = new CanReliveObj();
        obj = null;   //可复活
        System.out.println("第一次gc");
        //因为GC的时候会finalize方法，然后obj被复活了。
        System.gc();
        Thread.sleep(1000);
        if (obj == null)
        {
            System.out.println("obj 是 null");
        }
        else
        {
            System.out.println("obj 可用");
        }
        //然后执行一次GC，此时obj就被回收掉了，因为finalize方法只会执行一次
        obj = null;    //不可复活
        System.out.println("第二次gc");
        System.gc();
        Thread.sleep(1000);
        if (obj == null)
        {
            System.out.println("obj 是 null");
        }
        else
        {
            System.out.println("obj 可用");
        }
    }
}