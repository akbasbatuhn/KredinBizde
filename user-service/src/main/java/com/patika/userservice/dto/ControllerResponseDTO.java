package com.patika.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ControllerResponseDTO {

    private HttpStatus httpStatus;
    private String statusMessage;
}
