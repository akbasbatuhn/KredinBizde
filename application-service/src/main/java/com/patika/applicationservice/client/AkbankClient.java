package com.patika.applicationservice.client;

import com.patika.applicationservice.dto.client.request.BankApplicationRequest;
import com.patika.applicationservice.dto.client.response.LoanDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "akbank", url = "${akbank.url}")
public interface AkbankClient {

    @PostMapping("/api/akbank/v1/applications")
    ResponseEntity<LoanDetailsResponse> createApplication(BankApplicationRequest request);
}
