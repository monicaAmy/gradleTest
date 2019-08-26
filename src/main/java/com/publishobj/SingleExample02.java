package com.publishobj;

import com.annotation.ThreadSafe;

/**
 *
 */
@ThreadSafe
public class SingleExample02
{
    //volatile 阻止CPU指令重排 先执行3在执行2
    private volatile static SingleExample02 singleExample = null;

    private SingleExample02()
    {

    }

    public static SingleExample02 getInstance()
    {
        if (singleExample == null)
        {
            //改为锁代码块,提高性能
            synchronized (SingleExample.class)
            {
                //双重检测机制,防止一个线程进锁创建完后,另一个进程也进了锁,多次构造
                if (singleExample == null)
                {
                    /**
                     * 执行new操作的时候，CPU一共进行了三次指令
                     * （1）memory = allocate() 分配对象的内存空间  !=null
                     * （2）ctorInstance() 初始化对象
                     * （3）instance = memory 设置instance指向刚分配的内存
                     */
                    singleExample = new SingleExample02();
                }
            }
        }
        return singleExample;
    }
}
