package com.publishobj;

import com.annotation.Recommend;
import com.annotation.ThreadSafe;

/**
 * 最安全的方法
 * 比懒汉安全
 * 比饿汉:在实际调用的时候才会初始化,不会造成资源浪费
 */
@ThreadSafe
@Recommend
public class SingleExample05
{
    private SingleExample05()
    {

    }

    public static SingleExample05 getInstance()
    {
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton
    {
        //通过实例调用方法
        INSTANCE;

        private SingleExample05 singleton;

        //JVM 保证这个方法绝对只调用一次
        Singleton()
        {
            System.out.println("使用时,才会被加载...");
            singleton = new SingleExample05();
        }

        public SingleExample05 getSingleton()
        {
            return singleton;
        }
    }

    public static void main(String[] args)
    {
        System.out.println(getInstance().hashCode());
    }
}
