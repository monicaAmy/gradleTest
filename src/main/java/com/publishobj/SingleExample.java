package com.publishobj;

import com.annotation.NotThreadSafe;

/**
 * 单例模式 懒汉
 */
@NotThreadSafe
public class SingleExample
{
    // 静态值在静态方法中使用
    private static SingleExample singleExample = null;

    private SingleExample()
    {

    }

    /**
     * 静态方法,方便调用
     *
     * @return
     */
    public static SingleExample getInstance()
    {
        //判空时,可能导致构造函数执行两次,一旦构造函数中涉及到某些资源的处理，那么就会发生错误
        if (singleExample == null)
        {
            singleExample = new SingleExample();
        }
        return singleExample;
    }
}
