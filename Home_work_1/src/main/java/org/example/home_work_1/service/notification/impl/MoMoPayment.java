package org.example.home_work_1.service.notification.impl;

import org.example.home_work_1.service.notification.IPaymentMethod;
import org.springframework.stereotype.Component;

@Component("momoPayment")
public class MoMoPayment implements IPaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Đã thanh toán " + amount + " qua MoMo!");
    }
    @Override
    public String getMethodName() {
        return "MoMo";
    }
}
