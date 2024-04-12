package com.patika.bankservice.dto.response;

import com.patika.bankservice.enums.LoanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetLoanResponse {

    private Long id;
    private LoanType loanType;
    private Double interestRate;
}
