package com.lamdba;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MySetCcollector2<T> implements Collector<T, Set<T>, Map<T, T>>
{

    @Override
    public Supplier<Set<T>> supplier()
    {
        System.out.println("supplier invoked!");
        // return HashSet<T>::new;
        return () ->
        {
            System.out.println("-------------生成结果容器---------------");
            return new HashSet<>();
        };
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator()
    {
        System.out.println("accumulator invoked !");
        //return Set<T>::add;
        return (set, item) ->
        {
            //这里 打印 的时候遍历了set, 一个线程遍历,一个线程修改的时候就会有并发修改异常,所以不要在 accumulator 里面做别的操作
            System.out.println(set + "====================================" + Thread.currentThread().getName());
            set.add(item);
        };
    }

    @Override
    public BinaryOperator<Set<T>> combiner()
    {
        System.out.println("combiner invoked !");
        return (set1, set2) ->
        {
            //  加上 CONCURRENT ,就只在一个结果容器中进行累加, 所以 combiner没有执行
            System.out.println("set1==" + set1);
            System.out.println("set2==" + set2);
            set1.addAll(set2);
            return set1;
        };
    }

    // 中间结果容器和最终结果容器类型不一致,会调用 finisher来转换
    @Override
    public Function<Set<T>, Map<T, T>> finisher()
    {
        System.out.println("finisher invoked!");

        return set ->
        {
            //这里使用TreeMap就会排序
            HashMap<T, T> map = new HashMap<>();
            set.stream().forEach(item -> map.put(item, item));
            return map;
        };
    }

    @Override
    public Set<Characteristics> characteristics()
    {
        System.out.println("characteristics invoked !");
        //Characteristics.CONCURRENT 并行 ConcurrentModificationException 会有并行读写异常
        // CONCURRENT  不加 多个线程修改多个结果容器, 加上 多个线程修改同一个结果容器
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED, Characteristics.CONCURRENT));
        // return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));
    }

    public static void main(String[] args)
    {
        for (int i = 0; i < 1000; i++)
        {
            //中间用set<T> 流来转换，所以会去重
            List<String> list = Arrays.asList("nohai", "hello", "hello", "welcome", "Welcome");
            //parallelStream 使用 ForkJoinPool 处理, 最多生成 与CPU 核数相同的线程数 win 超线程 生成的核心数多于硬件核心数
            Map<String, String> collect = list.parallelStream().collect(new MySetCcollector2<String>());
            //有并行和串行  看最终结果 串行
            //  Map<String, String> collect = list.stream().parallel().sequential().collect(new MySetCcollector2<String>());
            collect.forEach((k, v) -> System.out.println(k + "=" + v));

            //Collectors   toList  toSet toCollection(传值)
            //无序同时中间结果容器值和最终结果类型一致的
            //EnumSet.of(Collector.Characteristics.UNORDERED,Collector.Characteristics.IDENTITY_FINISH)
        }

    }
}
