package org.example.home_work_1.service;

import org.example.home_work_1.service.notification.IPaymentMethod;
import org.example.home_work_1.service.payment.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final IPaymentMethod paymentMethod;
    private final NotificationService notificationService;

    @Autowired
    public OrderService(
            @Qualifier("momoPayment") IPaymentMethod paymentMethod,
            @Qualifier("smsNotification") NotificationService notificationService) {
        this.paymentMethod = paymentMethod;
        this.notificationService = notificationService;
    }

    public void processOrder(String customer, String product, double amount) {
        System.out.println("Khách hàng: " + customer + " | Sản phẩm: " + product);

        paymentMethod.pay(amount);

        String message = "Đơn hàng '" + product + "' trị giá " + amount + " VND đã được thanh toán qua " + paymentMethod.getMethodName() + ".";
        notificationService.sendNotification(customer, message);

    }
}