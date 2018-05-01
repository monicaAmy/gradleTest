package com.thread.queue;

/**
 * NieSu 2018/5/1
 */
public class PBTest implements Comparable<PBTest>
{
  private int id;
  private String name;

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

  @Override
  public int compareTo(PBTest o)
  {
    return this.id>o.id?0:-1;
  }

  @Override
  public String toString()
  {
    final StringBuilder sb = new StringBuilder("PBTest{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
