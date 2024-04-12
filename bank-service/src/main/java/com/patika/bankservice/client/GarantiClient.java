package com.patika.bankservice.client;

import com.patika.bankservice.dto.client.ApplicationRequest;
import com.patika.bankservice.dto.client.ApplicationResponse;
import com.patika.bankservice.dto.response.GetLoanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "garanti", url = "http://localhost:8091")
public interface GarantiClient {

    @PostMapping("/api/garanti/v1/applications")
    ResponseEntity<List<ApplicationResponse>> createApplication(ApplicationRequest request);

    @GetMapping("/api/garanti/v1/loans")
    ResponseEntity<List<GetLoanResponse>> getLoans();
}
