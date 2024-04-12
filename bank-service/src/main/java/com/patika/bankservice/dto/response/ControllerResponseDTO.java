package com.patika.bankservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ControllerResponseDTO {

    private HttpStatus httpStatus;
    private String statusMessage;
}
