package com.ztest;

import org.apache.http.util.TextUtils;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * NieSu 2018/8/12
 */
public class Fun
{

    @Test
    public void transTime()
    {
        try
        {
            String date = TimeStamp2Date(1380813172 + "", null); //2013/10/3 23:12:52

            System.out.println(date);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static String TimeStamp2Date(String timestampString, String formats)
    {
        if (TextUtils.isEmpty(formats))
            formats = "yyyy-MM-dd HH:mm:ss";
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format  如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String Date2TimeStamp(String dateStr, String format)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return nowTimeStamp
     */
    public static String getNowTimeStamp()
    {
        long time = System.currentTimeMillis();
        String nowTimeStamp = String.valueOf(time / 1000);
        return nowTimeStamp;
    }

    public static void main(String[] args) throws Exception
    {
        Map<Thread, StackTraceElement[]> maps = Thread.getAllStackTraces();

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (threadGroup.getParent() != null)
        {
            threadGroup = threadGroup.getParent();
        }
        int totalThread = threadGroup.activeCount();
        System.out.println(totalThread);

//    System.out.println(write.getName() + " 存活:" + write.isAlive() + " 状态:" + write.getState());
//    System.out.println("------------------");
//    System.out.println(read.getName() + " 存活:" + read.isAlive() + " 状态:" + read.getState());
    }

    public static int getRandom()
    {
        try
        {
            SecureRandom secureRandom = new SecureRandom();
            SecureRandom secureRandom3 = SecureRandom.getInstance("SHA1PRNG");
            SecureRandom secureRandom2 = SecureRandom.getInstance("SHA1PRNG", "SUN");
            int i = secureRandom2.nextInt();
            return i;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchProviderException e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    @Test
    public void fn()
    {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        //使用add会报错 queue full, 使用offer不会
        arrayBlockingQueue.offer("a1");
        arrayBlockingQueue.offer("a2");
        arrayBlockingQueue.offer("a3");
        arrayBlockingQueue.offer("a4");
        arrayBlockingQueue.offer("a5");
        arrayBlockingQueue.offer("a6");

        System.out.println(arrayBlockingQueue.size());

        Object poll = arrayBlockingQueue.poll();
        System.out.println("删除" + poll);

        System.out.println(arrayBlockingQueue.size());

    }

}

