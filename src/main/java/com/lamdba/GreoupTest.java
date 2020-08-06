package com.lamdba;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class GreoupTest
{
    private List<Student> students;

    @Before
    public void before()
    {
        Student zhangsan = new Student("zhangsan", 80);
        Student lisi = new Student("lisi", 90);
        Student wangwu = new Student("wangwu", 100);

        students = new ArrayList<Student>()
        {{
            add(zhangsan);
            add(lisi);
            add(wangwu);
        }};
    }

    //java.util.stream.Collectors.CollectorImpl
    @Test
    public void test()
    {
        //分组之后, 每个组有多少个
        Map<String, Long> collect = students.stream().collect(Collectors.groupingBy(Student::getName, counting()));
        collect.forEach((k, v) -> System.out.println(k + "===" + v));

        System.out.println("求每个组的平均值...");
        Map<String, Double> collect1 = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.averagingDouble(Student::getScore)));
        collect1.forEach((k, v) -> System.out.println(k + "===" + v));

        Map<Boolean, List<Student>> collect2 = students.stream().collect(Collectors.partitioningBy(student -> student.getScore() > 90));

        List<Student> students1 = collect2.get(true);
        List<Student> students2 = collect2.get(false);

        System.out.println("大于90分的 ===" + students1);

        System.out.println("count:" + students.stream().collect(counting()));
        System.out.println("count:" + students.stream().count());
        System.out.println("------------");
        students.stream().collect(minBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);
        students.stream().collect(maxBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);
        System.out.println(students.stream().collect(averagingInt(Student::getScore)));
        //270
        System.out.println("summingInt==============" + students.stream().collect(summingInt(Student::getScore)));
        //{count=3, sum=270, min=80, average=90.000000, max=100}
        IntSummaryStatistics intSummaryStatistics = students.stream().collect(summarizingInt(Student::getScore));
        System.out.println("summarizingInt==============" + intSummaryStatistics);

        String collect4 = students.stream().map(Student::getName).collect(joining(",", "{", "}"));

    }

    @Test
    public void testjisuan()
    {

    }

    @Test
    public void testNull()
    {
        Optional<Student> collect3 = students.stream().collect(minBy(Comparator.comparingInt(Student::getScore)));
        //找出最小值并打印
        //ifPresent 判断是否存在
        collect3.ifPresent(System.out::println);

        //判空
        // students.add(null);
        students.stream().filter(Objects::nonNull).forEach(person ->
        {
            System.out.println(person.getName());
        });

        Optional.ofNullable(students).orElseGet(() ->
        {
            System.out.println("personList为null！");
            return new ArrayList<>();
        }).stream().filter(Objects::nonNull).forEach(person ->
        {
            System.out.println(person.getName());
            System.out.println(person.getScore());
        });

        Map<String, Object> map = new HashMap<>();
        map.put("text", "123");
        String text = Optional.ofNullable(map.get("text"))
                .map(value -> value.toString().trim())
                .orElse("");
        System.out.println(text);
    }

    @Test
    public void testGroup()
    {
        Map<Integer, Map<String, List<Student>>> collect5 = students.stream().collect(groupingBy(Student::getScore, groupingBy(Student::getName)));

        Map<Boolean, Map<Boolean, List<Student>>> collect5P = students.stream().collect(partitioningBy(student -> student.getScore() > 80, partitioningBy(student -> student.getScore() > 90)));

        Map<String, Student> collect6 = students.stream().collect(groupingBy(Student::getName, collectingAndThen(minBy(Comparator.comparingInt(Student::getScore)), Optional::get)));

        System.out.println(collect6);
    }

}
