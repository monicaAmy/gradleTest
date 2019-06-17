package com.jvm;

public class HeapAllot
{
    public static void main(String[] args)
    {
        byte[] b = null;
        for (int i = 0; i < 10; i++)
        {
            b = new byte[1 * 1024 * 1024];
        }
    }
}

// -Xmx20m -Xms20m -Xmn15m  -XX:+PrintGCDetails
/*
[GC (Allocation Failure) [PSYoungGen: 11757K->1520K(13824K)] 11757K->1851K(18944K), 0.0009778 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        Heap
        PSYoungGen      total 13824K, used 2667K [0x00000000ff100000, 0x0000000100000000, 0x0000000100000000)
        eden space 12288K, 9% used [0x00000000ff100000,0x00000000ff21ed08,0x00000000ffd00000)
        from space 1536K, 98% used [0x00000000ffd00000,0x00000000ffe7c020,0x00000000ffe80000)
        to   space 1536K, 0% used [0x00000000ffe80000,0x00000000ffe80000,0x0000000100000000)
        ParOldGen       total 5120K, used 331K [0x00000000fec00000, 0x00000000ff100000, 0x00000000ff100000)
        object space 5120K, 6% used [0x00000000fec00000,0x00000000fec52e00,0x00000000ff100000)
        Metaspace       used 3227K, capacity 4496K, committed 4864K, reserved 1056768K
class space    used 350K, capacity 388K, committed 512K, reserved 1048576K*/

// -Xmx20m -Xms20m -Xmn7m  -XX:+PrintGCDetails
//最大堆，最小堆，新生代大小
/*
[GC (Allocation Failure) [PSYoungGen: 5284K->496K(6656K)] 5284K->1807K(19968K), 0.0010404 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        [GC (Allocation Failure) [PSYoungGen: 5796K->480K(6656K)] 7107K->2895K(19968K), 0.0007409 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        Heap
        PSYoungGen （年轻代）     total 6656K, used 2741K [0x00000000ff900000, 0x0000000100000000, 0x0000000100000000)
        eden space （新生代）6144K, 36% used [0x00000000ff900000,0x00000000ffb354d0,0x00000000fff00000)
        from space 512K, 93% used [0x00000000fff80000,0x00000000ffff8020,0x0000000100000000)
        to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
        ParOldGen （老年代）      total 13312K, used 2415K [0x00000000fec00000, 0x00000000ff900000, 0x00000000ff900000)
        object space 13312K, 18% used [0x00000000fec00000,0x00000000fee5be30,0x00000000ff900000)
        Metaspace （元数据）      used 3213K, capacity 4496K, committed 4864K, reserved 1056768K
class space    used 348K, capacity 388K, committed 512K, reserved 1048576K*/

//survivor（from,to）+eden=年轻代大小
//年轻代+老年代=heap区大小
//GC次数越少，GC实践越短，每次GC峰值图像一致