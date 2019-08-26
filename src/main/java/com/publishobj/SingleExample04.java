package com.publishobj;

import com.annotation.ThreadSafe;

@ThreadSafe
public class SingleExample04
{

    //静态域与静态块的顺序一定不要反 否则会空指针
    private static SingleExample04 singleExample = null;

    static
    {
        System.out.println("不管是否使用,都会被加载....");
        singleExample = new SingleExample04();
    }

    private SingleExample04()
    {

    }

    public static SingleExample04 getInstance()
    {
        return singleExample;
    }

    public static void main(String[] args)
    {
        // System.out.println(getInstance().hashCode());
        // System.out.println(getInstance().hashCode());
    }
}
