package com.proxy;

import com.proxy.factory.UsbKingSell;
import com.proxy.handler.MyHandler;
import com.proxy.service.UsbSell;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ShopUsbMain {

    public static void main(String[] args) {

        //创建代理对象，完成功能
        //第一步、创建一个目标类对象,父类（接口）类型定义
        // 作用：
        // 1、为下面创建InvocationHandler实现类对象时作为参数传进去，
        // 2、通过反射，获取目标类的类加载器，作为创建代理对象方法的参数
        // 3、通过反射，获取目标类的实现接口UsbSell的class
        UsbSell usbFactory = new UsbKingSell();

        //第二步、创建InvocationHandler接口实现类对象，把目标类对象传进去
        //作用：下面调用创建代理对象的方法中作为InvocationHandler对象参数传进去
        InvocationHandler myHandler = new MyHandler(usbFactory);//传谁，下面代码就给谁创建代理对象

        //第三步、创建代理对象,转换为目标类的实现接口类型（多态）
        //其中参数目标类实现接口的class获取可以用反射获取，接口可实现多个，方法返回的是一个数组
        // 该参数的作用就是使返回值能够强制转换成目标类的实现接口类型，然后可以.sell()方法
        UsbSell proxy = (UsbSell) Proxy.newProxyInstance(usbFactory.getClass().getClassLoader(),
                                                usbFactory.getClass().getInterfaces(),
                                                myHandler);
        //JDK动态代理创建的代理对象
        //System.out.println(proxy);//报错
        System.out.println("proxy:"+proxy.getClass().getName());//proxy:com.sun.proxy.$Proxy0

        //第四步、通过代理执行方法
        float price = proxy.sell(1);
        System.out.println("代理对象执行方法调用目标方法："+price);

    }

}
