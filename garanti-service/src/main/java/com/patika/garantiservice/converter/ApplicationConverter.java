package com.patika.garantiservice.converter;

import com.patika.garantiservice.dto.request.ApplicationRequest;
import com.patika.garantiservice.dto.response.ApplicationResponse;
import com.patika.garantiservice.entity.Application;
import com.patika.garantiservice.enums.ApplicationStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationConverter {

    public Application toApplication(ApplicationRequest request) {
        return Application.builder()
                .installment(request.getInstallment())
                .amount(request.getAmount())
                .applicationDate(LocalDateTime.now())
                .applicationStatus(ApplicationStatus.INITIAL)
                .build();
    }

    public ApplicationResponse toResponse(Application application) {
        return ApplicationResponse.builder()
                .id(application.getId())
                .applicationDate(application.getApplicationDate())
                .amount(application.getAmount())
                .installment(application.getInstallment())
                .repaymentAmount(application.getRepaymentAmount())
                .applicationStatus(application.getApplicationStatus())
                .loanType(application.getLoan().getLoanType())
                .build();
    }

    public List<ApplicationResponse> toResponseList(List<Application> applications) {
        List<ApplicationResponse> applicationResponses = new ArrayList<>();

        applications.forEach(application -> {
            applicationResponses.add(toResponse(application));
        });

        return applicationResponses;
    }
}
