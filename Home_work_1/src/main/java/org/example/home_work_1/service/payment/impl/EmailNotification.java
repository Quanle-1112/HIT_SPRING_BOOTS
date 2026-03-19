package org.example.home_work_1.service.payment.impl;

import org.example.home_work_1.service.payment.NotificationService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("emailNotification")
@Primary
public class EmailNotification implements NotificationService {
    @Override
    public void sendNotification(String to, String message) {
        System.out.println("Đã gửi email tới " +to +"\n" + message);
    }
}
