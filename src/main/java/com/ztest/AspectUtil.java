package com.ztest;

/**
 * NieSu 2018/8/12
 */
public class AspectUtil
{

  //采用cglib动态代理生成代理子对象,基于类的
 // @Test
  public void testCglibProxy(){
    //确定目标
    ReadAndWrite target = new ReadAndWrite();
    //通过代理工厂得到代理子对象
    CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(target);
    //生成目标类对象的子代理对象（子类）
    ReadAndWrite productService=(ReadAndWrite) cglibProxyFactory.getProxyObject();
    //调用代理子对象的方法，该方法已经被增强了
//    productService.save();
//    productService.findCount();
  }

}
