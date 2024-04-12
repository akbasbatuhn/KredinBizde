package com.patika.applicationservice.client;

import com.patika.applicationservice.dto.client.response.LoanDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "bank-service", url = "http://localhost:8079")
public interface BankServiceClient {

    @GetMapping("/api/v1/loans/{id}")
    ResponseEntity<LoanDetailsResponse> getLoanDetails(@PathVariable Long id);
}
