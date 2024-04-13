package com.patika.errorlogservice.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patika.errorlogservice.converter.ErrorLogConverter;
import com.patika.errorlogservice.dto.ExceptionResponse;
import com.patika.errorlogservice.entity.ErrorLog;
import com.patika.errorlogservice.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class KafkaListeners {

    private final LogService service;

    public KafkaListeners(LogService service) {
        this.service = service;
    }

    @KafkaListener(topics = "error-log", groupId = "groupId")
    void listener(String data) {
        processMessage(data);
    }

    public void processMessage(String jsonMessage) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ExceptionResponse exceptionResponse = objectMapper.readValue(jsonMessage, ExceptionResponse.class);

            ErrorLog created = ErrorLogConverter.toErrorLog(exceptionResponse);
            created.setErrorTimestamp(LocalDateTime.now());
            ErrorLog saved = service.save(created);

            log.info("Error saved with id: {}", saved.getId());
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
        }
    }
}
