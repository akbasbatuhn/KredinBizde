package com.patika.applicationservice.dto.converter;

import com.patika.applicationservice.dto.request.ApplicationRequest;
import com.patika.applicationservice.dto.response.ApplicationResponseDTO;
import com.patika.applicationservice.entity.Application;
import com.patika.applicationservice.enums.ApplicationStatus;

public class ApplicationDTOConverter {

    private ApplicationDTOConverter() {}

    public static ApplicationResponseDTO toResponseDTO(Application application) {
        return ApplicationResponseDTO.builder()
                .applicationStatus(application.getApplicationStatus())
                .userId(application.getUserId())
                .loanId(application.getLoanId())
                .id(application.getId())
                .build();
    }

    public static Application toApplication(ApplicationRequest request) {
        return Application.builder()
                .applicationStatus(ApplicationStatus.INITIAL)
                .loanId(request.getLoanId())
                .userId(request.getUserId())
                .amount(request.getAmount())
                .installment(request.getInstallment())
                .build();
    }
}
