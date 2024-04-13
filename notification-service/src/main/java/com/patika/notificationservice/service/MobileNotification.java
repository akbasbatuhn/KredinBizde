package com.patika.notificationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MobileNotification implements NotificationStrategy {
    @Override
    public void sendNotification(String message) {
        log.info("Mobile notification message: \"{}\"", message);
    }
}
