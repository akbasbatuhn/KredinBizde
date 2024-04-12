package com.patika.applicationservice.service;

import com.patika.applicationservice.client.AkbankClient;
import com.patika.applicationservice.client.BankServiceClient;
import com.patika.applicationservice.client.GarantiClient;
import com.patika.applicationservice.dto.client.request.BankApplicationRequest;
import com.patika.applicationservice.dto.client.response.LoanDetailsResponse;
import com.patika.applicationservice.dto.request.ApplicationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExternalAPIService {

    private final AkbankClient akbankClient;
    private final GarantiClient garantiClient;
    private final BankServiceClient bankServiceClient; // bankada o loan hangi loanId'de onu almak içins

    public ExternalAPIService(AkbankClient akbankClient, GarantiClient garantiClient, BankServiceClient bankServiceClient) {
        this.akbankClient = akbankClient;
        this.garantiClient = garantiClient;
        this.bankServiceClient = bankServiceClient;
    }

    public void createApplication(ApplicationRequest request) {
        if(request.getBankId() == 1) createGarantiApplication(request);
        else if(request.getBankId() == 2) createAkbankApplication(request);
    }

    public LoanDetailsResponse createGarantiApplication(ApplicationRequest request) {
        Long loanIdFromBank = getLoanDetails(request.getLoanId());
        BankApplicationRequest applicationRequest =
                new BankApplicationRequest(loanIdFromBank, request.getAmount(), request.getInstallment());

        return garantiClient.createApplication(applicationRequest).getBody();
    }

    public LoanDetailsResponse createAkbankApplication(ApplicationRequest request) {
        Long loanIdFromBank = getLoanDetails(request.getLoanId());
        BankApplicationRequest applicationRequest =
                new BankApplicationRequest(loanIdFromBank, request.getAmount(), request.getInstallment());

        return akbankClient.createApplication(applicationRequest).getBody();
    }

    private Long getLoanDetails(Long id) {
        LoanDetailsResponse response = bankServiceClient.getLoanDetails(id).getBody();
        return response.getLoanIdFromBank();
    }
}
