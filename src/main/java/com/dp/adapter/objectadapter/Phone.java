package com.dp.adapter.objectadapter;

public class Phone
{
    public void charging()
    {
        IV5 iv = new V220();
        VAdapter vAdapter = new VAdapter(iv);
        System.out.println(vAdapter.output());

        vAdapter.setIv(new V240());
        System.out.println(vAdapter.output());
    }

}
