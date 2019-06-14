package com.jvm;

public class OnStackTest
{
    public static void main(String[] args)
    {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000_000_000; i++)
        {
            alloc();
        }
        long l1 = System.currentTimeMillis();
        System.out.println("time:" + (l1 - l));
    }


    public static void alloc()
    {
        byte[] bytes = new byte[2];
        bytes[0] = 1;
    }
}


//Java栈 – 栈上分配
//        小对象（一般几十个bytes），在没有逃逸的情况（只有一个线程使用的时候，因为栈上的对象只能属于一个线程）下，可以直接分配在栈上

// 直接在运行配置时加上下面jvm参数
//运行程序 jvm自动优化内存分配 分配了三次
//-server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC

//运行程序在堆上分配内存  分配了很多次
//-server -Xmx10m -Xms10m -XX:-DoEscapeAnalysis -XX:+PrintGC

//[GC (Allocation Failure)  2048K->764K(9728K), 0.0009381 secs]

//GC：
// 表明进行了一次垃圾回收，前面没有Full修饰，表明这是一次Minor GC ,注意它不表示只GC新生代，并且现有的不管是新生代还是老年代都会STW。
// Allocation Failure：
// 表明本次引起GC的原因是因为在年轻代中没有足够的空间能够存储新的数据了


//522739K->156516K(1322496K)：
// 三个参数分别为：堆区垃圾回收前的大小，堆区垃圾回收后的大小，堆区总大小。
// 0.0025301 secs：
// 该内存区域GC耗时，单位是秒