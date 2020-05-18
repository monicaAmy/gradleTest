package com.dp;

import org.junit.Test;

public class SingletonTest01
{

    @Test
    public void test()
    {
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println(Singleton01.getInstance());
        System.out.println("Singleton02 ==" + Singleton02.getInstance());
        System.out.println(Singleton03.getInstance());
        System.out.println(Singleton04.getInstance());
        System.out.println(Singleton06.getInstance());
        System.out.println(Singleton06.getInstance());
        System.out.println(Singleton07.getInstance());
        System.out.println(Singleton07.getInstance());
        System.out.println(Singleton07.getInstance());
        System.out.println(Singleton07.getInstance());
        System.out.println(Singleton08.INSTANCE);
        System.out.println(Singleton08.INSTANCE);
    }
}

/**
 * 饿汉式 静态常量方式
 * 类加载的时候就完成了实例化,没有达到懒加载的效果
 * 没有用到这个实例,就会造成内存浪费
 */
class Singleton01
{
    private static final Singleton01 Singleton01 = new Singleton01();

    //构造器私有化
    private Singleton01()
    {
    }

    public static Singleton01 getInstance()
    {
        return Singleton01;
    }
}

/**
 * 饿汉式 静态代码块
 */
class Singleton02
{
    private static Singleton02 singleton02;

    static
    {
        singleton02 = new Singleton02();
    }

    private Singleton02()
    {

    }

    public static Singleton02 getInstance()
    {
        return singleton02;
    }
}

/**
 * 懒汉式 线程不安全
 * 起到了懒加载作用,只能在单线程使用
 */
class Singleton03
{
    private static Singleton03 singleton03;

    private Singleton03()
    {

    }

    public static Singleton03 getInstance()
    {
        if (singleton03 != null)
        {
            return singleton03;
        }
        else
        {
            singleton03 = new Singleton03();
            return singleton03;
        }
    }
}

/**
 * 懒汉式 线程安全
 */
class Singleton04
{
    private static Singleton04 singleton04;

    private Singleton04()
    {

    }

    public static synchronized Singleton04 getInstance()
    {
        if (singleton04 != null)
        {
            return singleton04;
        }
        else
        {
            //创建完还未赋值,会出现问题
            singleton04 = new Singleton04();
            return singleton04;
        }
    }

    //不推荐
    public static Singleton04 getInstance01()
    {
        if (singleton04 != null)
        {
            return singleton04;
        }
        else
        {
            synchronized (Singleton04.class)
            {
                singleton04 = new Singleton04();
            }
            return singleton04;
        }
    }

}

/**
 * 双重检查机制 两个判空,两个锁
 * 延迟加载
 * 保证了效率,推荐
 */
class Singleton06
{

    private static Singleton06 singleton06;

    private Singleton06()
    {

    }

    /**
     * 解决线程安全问题, 同时解决懒加载问题
     *
     * @return
     */
    public static synchronized Singleton06 getInstance()
    {
        if (null == singleton06)
        {
            synchronized (Singleton06.class)
            {
                if (null == singleton06)
                {
                    singleton06 = new Singleton06();
                }
            }
        }
        return singleton06;
    }
}

/**
 * 静态内部类 ,推荐
 * 利用类加载机制来保证初始化实例时只有一个线程
 */
class Singleton07
{
    //private static volatile Singleton07 instance;

    private Singleton07()
    {
    }

    private static class SingletonInstance
    {
        private static final Singleton07 INSTANCE = new Singleton07();
    }

    public static synchronized Singleton07 getInstance()
    {
        return SingletonInstance.INSTANCE;
    }
}

/**
 * 枚举 ,推荐
 * 线程安全,防止反序列化重新创建新的对象
 */
enum Singleton08
{
    INSTANCE;
    /*public void sayOK()
    {
        System.out.println("ok~");
    }*/
}