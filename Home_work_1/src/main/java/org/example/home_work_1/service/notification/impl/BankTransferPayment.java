package org.example.home_work_1.service.notification.impl;

import org.example.home_work_1.service.notification.IPaymentMethod;
import org.springframework.stereotype.Component;

@Component("bankTransferPayment")
public class BankTransferPayment implements IPaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Đã thanh toán " + amount + " qua ngân hàng!");
    }
    @Override
    public String getMethodName() {
        return "Bank Transfer";
    }
}
