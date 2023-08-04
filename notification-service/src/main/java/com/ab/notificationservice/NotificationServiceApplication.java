package com.ab.notificationservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(NotificationServiceApplication.class, args);

    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(FraudCheckEvent fraudCheckEvent){

        //Business Logic to Send notification
        log.info("Customer ID {} passed fraud check.", fraudCheckEvent.getCustomerId());

    }
}
