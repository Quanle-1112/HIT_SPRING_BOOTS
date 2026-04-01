package org.example.lesson2.exercise2;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppStartupService implements BeanNameAware {

    @Override
    public void setBeanName(String name) {
        System.out.println("==== 1. [BeanNameAware] Tên Bean là: " + name + " ====");
    }

    @PostConstruct
    public void init() {
        System.out.println("==== 2. [@PostConstruct] Application initialized at: " + LocalDateTime.now() + " ====");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("==== 3. [@PreDestroy] Application shutting down at: " + LocalDateTime.now() + " ====");
    }
}