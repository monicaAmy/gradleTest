package com.dp.builder;

public abstract class HouseBuilder
{
    private House house = new House();

    /**
     * 建造者不负责创建房子,给指挥者创建
     *
     * @return
     */
    public House build()
    {
//        buildBisic();
//        buildWalls();
//        roofed();
        return house;
    }

    public abstract void buildBisic();

    public abstract void buildWalls();

    public abstract void roofed();
}
