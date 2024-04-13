package com.patika.applicationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patika.applicationservice.dto.kafka.ExceptionResponse;
import com.patika.applicationservice.dto.kafka.Notification;
import com.patika.applicationservice.enums.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendExceptionResponseToKafka(ExceptionResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ExceptionResponse exceptionResponse =
                    new ExceptionResponse(response.getApiPath(), response.getErrorCode(), response.getErrorMessage());

            String json = objectMapper.writeValueAsString(exceptionResponse);

            kafkaTemplate.send("error-log", json);

            log.info("Error log sent");
        } catch (JsonProcessingException e) {
            System.err.println("Error converting object to JSON: " + e.getMessage());
        }
    }

    public void sendNotificationToKafka() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Notification emailNotification = new Notification(NotificationType.EMAIL, "Your application is sent to the bank");
            Notification smsNotification = new Notification(NotificationType.SMS, "Your application is sent to the bank");
            Notification mobileNotification = new Notification(NotificationType.MOBILE_NOTIFICATION, "Your application is sent to the bank");
            String emailJson = objectMapper.writeValueAsString(emailNotification);
            String smsJson = objectMapper.writeValueAsString(smsNotification);
            String mobileNotificationJson = objectMapper.writeValueAsString(mobileNotification);

            kafkaTemplate.send("notification", emailJson);
            kafkaTemplate.send("notification", smsJson);
            kafkaTemplate.send("notification", mobileNotificationJson);

            log.info("Notification sent");
        } catch (JsonProcessingException e) {
            log.error("Error converting object to JSON: " + e.getMessage());
        }
    }
}
