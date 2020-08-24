package com.lamdba;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 使用Future里面join切分成若干小任务执行，需要无状态对象
 * 不能被重复消费，每次消费都生成一个新流
 */
public class StreamTest
{
    public static void main(String[] args)
    {
        Stream<Integer> stream = Stream.iterate(1, item -> item + 2).limit(5);
        // int取最大才不要比较器，Integer取最大要比较器
        // stream.filter(item -> item > 2).mapToInt(item -> item * 2).skip(2).limit(2).max().ifPresent(System.out::println);
        //mapToInt 不能有空值
        // stream.filter(item->item>200).mapToInt(item ->item*2).forEach(System.out::println);
        //   stream.filter(item -> item > 200).map(item -> item * 2).forEach(System.out::println);

        IntSummaryStatistics intSummaryStatistics = stream.filter(item -> item > 2).mapToInt(item -> item * 2).skip(2).limit(2).summaryStatistics();

        double average = intSummaryStatistics.getAverage();

        System.out.println(average);

        //回创建一个无限流 distinct一直等待流结束， 要把limit放在前面
        // IntStream.iterate(0,i->(i+1)%2).distinct().limit(6).forEach(System.out::println);
        IntStream.iterate(0, i -> (i + 1) % 2).limit(6).distinct().forEach(System.out::println);
    }

    @Test
    public void testDu()
    {
        List<String> list = Arrays.asList("hello1", "world", "hello world");
        list.stream().mapToInt(item -> item.length()).filter(length -> length == 5).findFirst().ifPresent(System.out::println);

        System.out.println("======================================");
        //流内部优化，item只打印到length=5的字符串
        list.stream().mapToInt(item ->
        {
            int lenth = item.length();
            System.out.println(item);
            return lenth;
        }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);
    }

    @Test
    public void testDint()
    {
        //    List<String> list = Arrays.asList("hello", "world", "hello world", "hello");
//        list.stream().distinct().forEach(System.out::println);
        List<String> list2 = Arrays.asList("hello", "world", "hello world");
        list2.stream().map(item -> item.split(" ")).distinct().forEach(System.out::println);
        //分割之后变成三个数组对象
        list2.stream().map(item -> item.split(" ")).forEach(System.out::println);
        //数组转成String放进流里面
        list2.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct().forEach(System.out::println);
    }

    @Test
    public void testGroup()
    {
        Student zhangsan = new Student("zhangsan", 100, 20);
        Student lisi = new Student("lisi", 90, 20);
        Student wangwu = new Student("wangwu", 90, 30);
        Student zhangsan1 = new Student("zhangsan", 80, 40);

        List<Student> students = new ArrayList<Student>()
        {{
            add(zhangsan);
            add(lisi);
            add(wangwu);
            add(zhangsan1);
        }};

        Map<String, Long> collect = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
        Map<String, Double> collect2 = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.averagingDouble(Student::getScore)));

//        collect.forEach((k,v)->{
//            System.out.println(k+"="+v);
//        });
//
//        collect2.forEach((k,v)->{
//            System.out.println(k+"="+v);
//        });

        //分区
        Map<Boolean, List<Student>> collect1 = students.stream().collect(Collectors.partitioningBy(student -> student.getScore() >= 90));
        collect1.forEach((k, v) -> System.out.println(k + "=" + v));
    }

}
