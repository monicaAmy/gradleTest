package com.dp.builder;

public class Client
{
    public static void main(String[] args)
    {
        HouseDirector houseDirector = new HouseDirector(new CommonHouseBuilder());
        House commonHouse = houseDirector.buildHouse();

        houseDirector.setHouseBuilder(new HighHouseBuilder());
        House highHouse = houseDirector.buildHouse();

    }
}
