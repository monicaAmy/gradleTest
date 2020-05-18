package com.dp;

/**
 * 依赖倒转
 */
public class DependecyInversion
{
    public static void main(String[] args)
    {
        Person person = new Person();
        person.receive(new Email());

    }
}

class Email
{
    public String getInfo()
    {
        return "email 信息...";
    }
}

class Person
{
    public void receive(Email email)
    {
        System.out.println(email.getInfo());
    }
}