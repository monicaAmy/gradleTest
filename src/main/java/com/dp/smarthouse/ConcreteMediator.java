package com.dp.smarthouse;

import java.util.HashMap;

//具体的中介者类
public class ConcreteMediator extends Mediator
{
    //集合，放入所有的同事对象
    private HashMap<String, Colleague> colleagueMap;

    public ConcreteMediator()
    {
        colleagueMap = new HashMap<String, Colleague>();
    }

    @Override
    public void Register(String colleagueName, Colleague colleague)
    {
        colleagueMap.put(colleagueName, colleague);

    }

    //具体中介者的核心方法
    //1. 根据得到消息，完成对应任务
    //2. 中介者在这个方法，协调各个具体的同事对象，完成任务
    @Override
    public void GetMessage(int stateChange, String colleagueName)
    {
        //处理闹钟发出的消息
        if (colleagueMap.get(colleagueName) instanceof Alarm)
        {
            if (stateChange == 0)
            {
                ((CoffeeMachine) (colleagueMap.get("CoffeeMachine"))).StartCoffee();
                ((TV) (colleagueMap.get("TV"))).StartTv();
            }
            else if (stateChange == 1)
            {
                ((TV) (colleagueMap.get("TV"))).StopTv();
            }

        }
        else if (colleagueMap.get(colleagueName) instanceof CoffeeMachine)
        {
            ((Curtains) (colleagueMap.get("Curtains")))
                    .UpCurtains();

        }
        else if (colleagueMap.get(colleagueName) instanceof TV)
        {//如果TV发现消息

        }
        else if (colleagueMap.get(colleagueName) instanceof Curtains)
        {
            //如果是以窗帘发出的消息，这里处理...
        }

    }

    @Override
    public void SendMessage()
    {

    }

}
