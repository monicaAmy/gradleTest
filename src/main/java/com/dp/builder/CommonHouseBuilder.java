package com.dp.builder;

public class CommonHouseBuilder extends HouseBuilder
{
    private static final String COMMONHOUSE = "普通房子";

    @Override
    public void buildBisic()
    {
        String METHOD = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println(COMMONHOUSE + METHOD);
    }

    @Override
    public void buildWalls()
    {
        String METHOD = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println(COMMONHOUSE + METHOD);
    }

    @Override
    public void roofed()
    {
        String METHOD = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println(COMMONHOUSE + METHOD);

    }
}
