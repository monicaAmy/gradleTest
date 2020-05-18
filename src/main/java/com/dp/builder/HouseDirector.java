package com.dp.builder;

public class HouseDirector
{
    private HouseBuilder houseBuilder = null;

    public HouseDirector(HouseBuilder houseBuilder)
    {
        this.houseBuilder = houseBuilder;
    }

    public void setHouseBuilder(HouseBuilder houseBuilder)
    {
        this.houseBuilder = houseBuilder;
    }

    public House buildHouse()
    {
        houseBuilder.buildWalls();
        houseBuilder.buildBisic();
        houseBuilder.roofed();
        return houseBuilder.build();
    }
}
