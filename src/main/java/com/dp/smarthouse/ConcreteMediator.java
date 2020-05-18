package com.dp.smarthouse;

import java.util.HashMap;

//������н�����
public class ConcreteMediator extends Mediator
{
    //���ϣ��������е�ͬ�¶���
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

    //�����н��ߵĺ��ķ���
    //1. ���ݵõ���Ϣ����ɶ�Ӧ����
    //2. �н��������������Э�����������ͬ�¶����������
    @Override
    public void GetMessage(int stateChange, String colleagueName)
    {
        //�������ӷ�������Ϣ
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
        {//���TV������Ϣ

        }
        else if (colleagueMap.get(colleagueName) instanceof Curtains)
        {
            //������Դ�����������Ϣ�����ﴦ��...
        }

    }

    @Override
    public void SendMessage()
    {

    }

}
