package com.patika.akbankservice.dto.request;

import com.patika.akbankservice.enums.LoanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {

    private LoanType loanType;
    private Double interestRate;
}
