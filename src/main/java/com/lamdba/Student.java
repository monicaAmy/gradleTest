package com.lamdba;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Student
{
    @EqualsAndHashCode.Include
    private String name;

    private int score;

    private int level;

    public Student(String name, int score)
    {
        this.name = name;
        this.score = score;
    }
}
