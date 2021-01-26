package com.proxy.factory;

import com.proxy.service.UsbSell;

//目标类：金士顿U盘厂家
public class UsbKingSell implements UsbSell {
    @Override
    public float sell(int amount) {
        //目标类方法：功能是出售U盘，每个85
        System.out.println("目标类中sell方法执行");
        return 85f;
    }
}
