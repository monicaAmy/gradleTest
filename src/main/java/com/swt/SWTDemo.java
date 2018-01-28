package com.swt;

public class SWTDemo
{

  public static void main(String[] args)
  {
     javax.swing.JFrame jf =new javax.swing.JFrame();

    javax.swing.JPanel jp1 =new javax.swing.JPanel();
    javax.swing.JTextArea jta1=new javax.swing.JTextArea(5,30);
    jta1.setLineWrap(true);

    javax.swing.JPanel jp2 =new javax.swing.JPanel();
    java.awt.Button button=new java.awt.Button("encode");
    button.setSize(1,1);
    java.awt.Button button2=new java.awt.Button("decode");

    javax.swing.JPanel jp3 =new javax.swing.JPanel();
    javax.swing.JTextArea jta2=new javax.swing.JTextArea(5,30);
    jta2.setLineWrap(true);

    jp1.add("Center",jta1);

    jp2.add("West",button);
    jp2.add("East",button2);

    jp3.add("Center",jta2);

    jf.add("North",jp1);
    jf.add("West",jp2);
    jf.add("South",jp3);


    jf.setSize(400,400);
    jf.setVisible(true);

  }
}
