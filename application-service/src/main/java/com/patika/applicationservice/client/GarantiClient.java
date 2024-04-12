package com.patika.applicationservice.client;

import com.patika.applicationservice.dto.client.request.BankApplicationRequest;
import com.patika.applicationservice.dto.client.response.LoanDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "garanti", url = "http://localhost:8091")
public interface GarantiClient {

    @PostMapping("/api/garanti/v1/applications")
    ResponseEntity<LoanDetailsResponse> createApplication(BankApplicationRequest request);
}
