package com.patika.akbankservice.converter;

import com.patika.akbankservice.dto.request.LoanRequest;
import com.patika.akbankservice.dto.response.LoanResponse;
import com.patika.akbankservice.entity.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanConverter {

    private LoanConverter() {}

    public LoanResponse toResponse(Loan loan) {
        return LoanResponse.builder()
                .id(loan.getId())
                .loanType(loan.getLoanType())
                .interestRate(loan.getInterestRate())
                .build();
    }

    public Loan toLoan(LoanRequest request) {
        return Loan.builder()
                .loanType(request.getLoanType())
                .interestRate(request.getInterestRate())
                .build();
    }
}
