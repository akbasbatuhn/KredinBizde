package com.patika.errorlogservice.converter;

import com.patika.errorlogservice.dto.ExceptionResponse;
import com.patika.errorlogservice.entity.ErrorLog;

public class ErrorLogConverter {

    private ErrorLogConverter() {}

    public static ErrorLog toErrorLog(ExceptionResponse response) {
        return ErrorLog.builder()
                .errorCode(response.getErrorCode())
                .errorMessage(response.getErrorMessage())
                .apiPath(response.getApiPath())
                .build();
    }
}
