package com.patika.notificationservice.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patika.notificationservice.dto.NotificationDTO;
import com.patika.notificationservice.dto.enums.NotificationType;
import com.patika.notificationservice.service.NotificationStrategy;
import com.patika.notificationservice.service.NotificationStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaListeners {

    private final NotificationStrategyFactory factory;

    public KafkaListeners(NotificationStrategyFactory factory) {
        this.factory = factory;
    }

    @KafkaListener(topics = "notification", groupId = "groupId")
    void listener(String data) {
        processMessage(data);
    }

    public void processMessage(String jsonMessage) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            NotificationDTO notificationDTO = objectMapper.readValue(jsonMessage, NotificationDTO.class);

            sendNotification(notificationDTO.getNotificationType(), notificationDTO.getMessage());
        } catch (Exception e) {
            log.error("Error processing message: " + e.getMessage());
        }
    }

    public void sendNotification(NotificationType type, String message) {
        NotificationStrategy strategy = this.factory.getStrategy(type);

        if (strategy == null) {
            throw new IllegalArgumentException("Invalid notification type: " + type);
        }

        strategy.sendNotification(message);
    }
}
