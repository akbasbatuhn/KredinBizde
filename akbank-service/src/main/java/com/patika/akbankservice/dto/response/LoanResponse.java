package com.patika.akbankservice.dto.response;

import com.patika.akbankservice.enums.LoanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponse {

    private Long id;
    private LoanType loanType;
    private Double interestRate;
}
