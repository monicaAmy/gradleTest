package com.dp;

/**
 * 单一职责, Vehicle此类设计实现了两个功能,不符合单一职责
 */
public class SingleResponseibilty1
{
    public static void main(String[] args)
    {
        Vehicle vehicle = new Vehicle();
        vehicle.run("汽车");
        vehicle.run("飞机");
    }
}

class Vehicle
{
    public void run(String vehicle)
    {
        System.out.println(vehicle + "在公路上运行....");
    }
}
