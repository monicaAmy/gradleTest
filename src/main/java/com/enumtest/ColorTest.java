package com.enumtest;

import org.junit.Test;

public class ColorTest
{

    @Test
    public void base()
    {
        Color blue = Color.Blue;
        System.out.println(blue);

        Color red = Color.valueOf("Red");
        System.out.println(red);

        System.out.println("--------------for----------------");
        for (Color value : Color.values())
        {
            System.out.println(value);
        }
    }

    @Test
    public void testSwitch()
    {
        Color blue = Color.Purper;
        switch (blue)
        {
            case Red:
                System.out.println("red");
                break;
            case Blue:
                System.out.println("blue");
                break;
            case White:
                System.out.println("white");
                break;
            default:
                System.out.println("none");
                break;
        }

    }

    /**
     * enum相当于定义了class,interface,
     * 并举出了实例变量
     */
    public enum Color
    {
        //每一个类型都是Color类型的,默认public static final
        Red,
        White,
        Purper,
        //最后的;要不要都行
        Blue;
    }

    @Test
    public void testj()
    {
        Coin a = Coin.valueOf("A");
        //A
        System.out.println(a);
        //a
        System.out.println(a.getValue());
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
            this.value = value;
        }

    }
}
