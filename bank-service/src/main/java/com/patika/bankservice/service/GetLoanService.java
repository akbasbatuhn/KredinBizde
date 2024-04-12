package com.patika.bankservice.service;

import com.patika.bankservice.client.AkbankClient;
import com.patika.bankservice.client.GarantiClient;
import com.patika.bankservice.dto.response.GetLoanResponse;
import com.patika.bankservice.entity.Bank;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetLoanService {

    private final BankService bankService;
    private final LoanService loanService;
    private final AkbankClient akbankClient;
    private final GarantiClient garantiClient;

    public GetLoanService(BankService bankService, LoanService loanService, AkbankClient akbankClient, GarantiClient garantiClient) {
        this.bankService = bankService;
        this.loanService = loanService;
        this.akbankClient = akbankClient;
        this.garantiClient = garantiClient;
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void getLoans() {
        List<GetLoanResponse> garantiLoanList = garantiClient.getLoans().getBody();
        List<GetLoanResponse> akbankLoanList = akbankClient.getLoans().getBody();

        Bank garanti = bankService.getBankByName("Garanti");
        Bank akbank = bankService.getBankByName("Akbank");

        garantiLoanList.stream()
                .filter(loan -> !loanService.isLoanExist(loan.getId(), garanti.getId()))
                .forEach(loan -> loanService.createLoan(
                        new GetLoanResponse(loan.getId(), loan.getLoanType(), loan.getInterestRate()),
                        garanti
                ));

        akbankLoanList.stream()
                .filter(loan -> !loanService.isLoanExist(loan.getId(), akbank.getId()))
                .forEach(loan -> loanService.createLoan(
                        new GetLoanResponse(loan.getId(), loan.getLoanType(), loan.getInterestRate()),
                        akbank
                ));
    }
}
