package com.proxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//实现InvocationHandler接口，完成代理类要做的功能
public class MyHandler implements InvocationHandler {

    //因为是动态代理，目标类是不固定的，这里创建一个目标类对象的获取变量
    //通过构造方法传不同的目标类对象参数
    private Object target = null;

    public MyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object res = null;
        //代理类要做的功能
        //1、目标类方法（这里是sell）的调用，通过JDK反射获取目标类方法sell的Method对象调用
        //target可以代表各式各样的目标类的对象
        res = method.invoke(target,args);//执行目标方法

        //2、功能增强，原有目标方法可以实现的功能基础上，增加我自己需要的其他功能
        //增强1、这里商家加价出售给用户
        if (res != null) {

            //invoke方法返回的是Object，所需要强转
            float price = (float)res+25;
            res = price;

        }
        //增强2、给用户一些红包、优惠卷等等
        System.out.println("赠送优惠卷：满100减30");

        return res;
    }
}
