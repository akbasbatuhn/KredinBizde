package com.patika.notificationservice.service;

import com.patika.notificationservice.dto.enums.NotificationType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NotificationStrategyFactory {

    private final Map<NotificationType, NotificationStrategy> strategyMap;

    public NotificationStrategyFactory() {
        this.strategyMap = new HashMap<>();
        this.strategyMap.put(NotificationType.SMS, new SMSNotification());
        this.strategyMap.put(NotificationType.EMAIL, new EmailNotification());
        this.strategyMap.put(NotificationType.MOBILE_NOTIFICATION, new MobileNotification());
    }

    public NotificationStrategy getStrategy(NotificationType type) {
        return this.strategyMap.get(type);
    }
}
