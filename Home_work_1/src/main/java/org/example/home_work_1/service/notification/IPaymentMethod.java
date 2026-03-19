package org.example.home_work_1.service.notification;

public interface IPaymentMethod {
    void pay(double amount);
    String getMethodName();
}
