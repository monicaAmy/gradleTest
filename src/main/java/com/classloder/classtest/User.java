package com.classloder.classtest;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable
{
    public static double total = 0;
    int id;
    private String name;
    public int age;
    protected double score;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public double getScore()
    {
        return score;
    }

    public void setScore(double score)
    {
        this.score = score;
    }

    public int test(String name, Object object)
    {
        return -1;
    }

}
