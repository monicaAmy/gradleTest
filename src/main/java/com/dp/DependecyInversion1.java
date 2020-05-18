package com.dp;

public class DependecyInversion1
{
    public static void main(String[] args)
    {
        Person1 person = new Person1();
        person.receive(new Email1());
        person.receive(new WX());

    }
}

interface Receiver
{
    public String getInfo();
}

class Email1 implements Receiver
{
    public String getInfo()
    {
        return "email 信息...";
    }
}

class WX implements Receiver
{
    @Override
    public String getInfo()
    {
        return "wx 信息...";
    }
}

class Person1
{
    public void receive(Receiver r)
    {
        System.out.println(r.getInfo());
    }
}
