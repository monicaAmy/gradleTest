package com.ztest;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

//cglib动态代理工厂：用来生成代理对象
public class CglibProxyFactory implements MethodInterceptor
{

  private Object target;

  public CglibProxyFactory(Object target)
  {
    super();
    this.target = target;
  }

  public Object getProxyObject()
  {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(target.getClass());
    enhancer.setCallback(this);
    return enhancer.create();
  }


  @Override
  //实现方法
  //参数1：代理子对象，参数2：目标的方法对象，参数3：目标的方法的参数，参数4：代理对象的方法（已经被增强过的）
  public Object intercept(Object proxy, Method method, Object[] args,
      MethodProxy methodProxy) throws Throwable
  {
    //增强代码
    System.out.println("come in....");
    Object o = methodProxy.invokeSuper(proxy, args);
    System.out.println("come out....");
    return o;

  }

}
