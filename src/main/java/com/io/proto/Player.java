package com.io.proto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//public class Player implements Serializable
public class Player extends Serializer
{
    private long playerId;

    private int age;

    private String name;

    private List<Integer> skills = new ArrayList<>();

    private Resource resource = new Resource();

    public Player(long playerId, int age, String name)
    {
        this.playerId = playerId;
        this.age = age;
        this.name = name;
    }

    @Override
    protected void read()
    {
        this.playerId = readLong();
        this.age = readInt();
        this.skills = readList(Integer.class);
        this.resource = read(Resource.class);
    }

    @Override
    protected void write()
    {
        writeLong(playerId);
        writeInt(age);
        writeList(skills);
        writeObject(resource);
    }
}
