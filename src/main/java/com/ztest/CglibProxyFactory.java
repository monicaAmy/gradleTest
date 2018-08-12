package com.ztest;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

//cglib动态代理工厂：用来生成代理对象
public class CglibProxyFactory implements MethodInterceptor
{

  //注入目标对象
  private Object target;

  public CglibProxyFactory(Object target)
  {
    super();
    this.target = target;
  }


  //得到代理对象
  public Object getProxyObject()
  {
    //1.实例化cglib代理增强器-
    Enhancer enhancer = new Enhancer();

    //2.在增强器上设置相应的属性值
    //设置目标的类:通过目标类对象来生成代理子对象
    enhancer.setSuperclass(target.getClass());
    //设置回调方法函数,参数：回调的对象（写增强方法的代码）
    enhancer.setCallback(this);
//Callback
    //3.-通过增强器得到代理对象
    return enhancer.create();
  }


  @Override
  //实现方法
  //参数1：代理子对象，参数2：目标的方法对象，参数3：目标的方法的参数，参数4：代理对象的方法（已经被增强过的）
  public Object intercept(Object proxy, Method method, Object[] args,
      MethodProxy methodProxy) throws Throwable
  {
    //增强代码
    System.out.println("记录日志了。。。。");
//		return method.invoke(target, args);//直接调用目标对象原来的方法，返回目标对象。相当于放行。
    return methodProxy.invokeSuper(proxy, args);//调用代理对象的父类的方法--》目标对象

  }

}
