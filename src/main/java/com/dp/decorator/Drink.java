package com.dp.decorator;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Drink
{
    public String des;
    private float price = 0.0f;

    public abstract float cost();
}
