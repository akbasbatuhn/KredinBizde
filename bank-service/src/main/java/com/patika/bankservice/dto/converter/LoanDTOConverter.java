package com.patika.bankservice.dto.converter;

import com.patika.bankservice.dto.response.GetLoanResponse;
import com.patika.bankservice.dto.response.LoanResponseDTO;
import com.patika.bankservice.entity.Bank;
import com.patika.bankservice.entity.Loan;

public class LoanDTOConverter {

    private LoanDTOConverter() {}

    public static LoanResponseDTO toResponse(Loan loan) {
        return LoanResponseDTO.builder()
                .id(loan.getId())
                .interestRate(loan.getInterestRate())
                .loanType(loan.getLoanType())
                .loanIdFromBank(loan.getLoanIdFromBank())
                .bankId(loan.getBank().getId())
                .build();
    }

    public static Loan toLoan(GetLoanResponse response, Bank bank) {
        return Loan.builder()
                .bank(bank)
                .loanIdFromBank(response.getId())
                .loanType(response.getLoanType())
                .interestRate(response.getInterestRate())
                .build();
    }
}
