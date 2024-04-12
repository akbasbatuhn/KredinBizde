package com.patika.garantiservice.dto.request;

import com.patika.garantiservice.enums.LoanType;
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
