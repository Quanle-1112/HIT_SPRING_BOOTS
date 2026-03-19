package org.example.home_work_1.service.payment.impl;

import org.example.home_work_1.service.payment.NotificationService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("smsNotification")
@Primary
public class SmsNotification implements NotificationService {
    @Override
    public void sendNotification(String to, String message) {
        System.out.println("Đã gửi tin nhắn đến " + to + "\n" + message);
    }
}
