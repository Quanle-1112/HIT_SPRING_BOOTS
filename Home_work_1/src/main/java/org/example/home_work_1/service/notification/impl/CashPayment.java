package org.example.home_work_1.service.notification.impl;

import org.example.home_work_1.service.notification.IPaymentMethod;
import org.springframework.stereotype.Component;

@Component("cashPayment")
public class CashPayment implements IPaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Đã thanh toán " + amount + " bằng tiền mặt");
    }
    @Override
    public String getMethodName() {
        return "Cash";
    }
}
