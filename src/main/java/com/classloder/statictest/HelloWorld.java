package com.classloder.statictest;

import org.junit.Test;

/**
 * 初始化顺序依次是（静态变量、静态初始化块）>（变量、初始化块）>构造器。
 */
public class HelloWorld
{
    //Java 中被 static 修饰的成员称为静态成员或类成员。它属于整个类所有，而不是某个对象所有，即被类的所有对象所共享。
    static String hobby = "imooc";

    String name; // 声明变量name
    String sex; // 声明变量sex
    static int age;// 声明静态变量age

    // 静态初始化块,只加载一次
    static
    {
        System.out.println("通过静态初始化块初始化age");
        age = 20;
    }

    // 初始化块,每创建一个对象就执行一次
    {
        System.out.println("通过初始化块初始化sex");
        sex = "男";
    }

    // 构造方法,每创建一个对象就执行一次
    public HelloWorld()
    {
        System.out.println("通过构造方法初始化name");
        name = "tom";
    }

    public void show()
    {
        System.out.println("姓名：" + name + "，性别：" + sex + "，年龄：" + age);
    }

    @Test
    public void testj()
    {
        HelloWorld helloWorld = new HelloWorld();
        HelloWorld helloWorld2 = new HelloWorld();
//imooc
        System.out.println(HelloWorld.hobby);
        System.out.println(helloWorld.hobby);
        System.out.println(helloWorld2.hobby);

        helloWorld.hobby = "jjjj";
//jjjj
        System.out.println(HelloWorld.hobby);
        System.out.println(helloWorld.hobby);
        System.out.println(helloWorld2.hobby);
    }

    /**
     * 注释掉之后只会执行静态初始化代码块
     *
     * @param args
     */
    public static void main(String[] args)
    {

        // 创建对象
        //HelloWorld hello = new HelloWorld();
        // 调用对象的show方法
        // hello.show();

    }

    public enum Coin
    {
        A("a"), B("b");

        private String value;

        public String getValue()
        {
            return value;
        }

        //必须要有,默认私有
        Coin(String value)
        {
            System.out.println("rrr");
            this.value = value;
        }

    }
}
