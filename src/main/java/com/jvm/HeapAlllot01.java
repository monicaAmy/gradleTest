package com.jvm;

import java.util.Vector;

public class HeapAlllot01
{
    public static void main(String[] args)
    {
        Vector v = new Vector();
        for (int i = 0; i < 25; i++)
            v.add(new byte[1 * 1024 * 1024]);
    }
}
//当发生内存溢出时， 保留dump文件到d:/a.dump
// -Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/a.dump //d盘下面不能已有a.dump
//使用jdk自带的visualVM，其在JDK_HOME/bin目录下，可搜：jvisualvm。注意：windows系统是jvisualvm.exe文件。文件→装入