package com.patika.applicationservice.exception;

import com.patika.applicationservice.dto.ErrorResponseDTO;
import com.patika.applicationservice.dto.kafka.ExceptionResponse;
import com.patika.applicationservice.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    private KafkaService kafkaService;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(
            ResourceNotFoundException exception, WebRequest request) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .errorCode(HttpStatus.NOT_FOUND)
                .errorMessage(exception.getMessage())
                .apiPath(request.getDescription(false))
                .errorTimestamp(LocalDateTime.now())
                .build();

        kafkaService.sendExceptionResponseToKafka(
                new ExceptionResponse(errorResponse.getApiPath(), errorResponse.getErrorCode(), errorResponse.getErrorMessage()));

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceAlreadyExistException(
            ResourceAlreadyExistException exception, WebRequest request) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .errorCode(HttpStatus.BAD_REQUEST)
                .errorMessage(exception.getMessage())
                .apiPath(request.getDescription(false))
                .errorTimestamp(LocalDateTime.now())
                .build();

        kafkaService.sendExceptionResponseToKafka(
                new ExceptionResponse(errorResponse.getApiPath(), errorResponse.getErrorCode(), errorResponse.getErrorMessage()));

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
