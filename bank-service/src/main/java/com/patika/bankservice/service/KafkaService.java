package com.patika.bankservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patika.bankservice.dto.kafka.ExceptionResponse;
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
}
