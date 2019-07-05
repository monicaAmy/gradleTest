package com.io.proto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Player implements Serializable
{
    private long playerId;

    private int age;

    private String name;

    private List<Integer> skills = new ArrayList<>();

    public Player(long playerId, int age, String name)
    {
        this.playerId = playerId;
        this.age = age;
        this.name = name;
    }
}
