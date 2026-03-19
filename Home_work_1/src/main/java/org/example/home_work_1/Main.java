package org.example.home_work_1;

import org.example.home_work_1.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private final OrderService orderService;

    public Main(OrderService orderService) {
        this.orderService = orderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        orderService.processOrder("Nguyen Van A", "Laptop ThinkPad", 25000000);
        orderService.processOrder("Nguyen Van B", "Laptop", 30000000);
        orderService.processOrder("Nguyen Van C", "ThinkPad", 35000000);
        orderService.processOrder("Nguyen Van D", "Dell", 27000000);
    }
}