package com.lamdba;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompareTest
{
    public static void main(String[] args)
    {
        List<String> list = Arrays.asList("nohai", "Hello", "hello", "welcome", "Welcome");
        //降序
        list.sort(Comparator.comparingInt(String::length).reversed());

        //前面的相等时候无法比较出顺序，才会用到后面的比较器
        //  Collections.sort(list,Comparator.comparing(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));
        Collections.sort(list, Comparator.comparing(String::length).reversed());
        System.out.println(list);
        Collections.sort(list, Comparator.comparing(String::length).reversed().thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));
        Collections.sort(list, Comparator.comparing(String::length).reversed().thenComparing(Comparator.reverseOrder()));

        System.out.println(list);
    }
}
