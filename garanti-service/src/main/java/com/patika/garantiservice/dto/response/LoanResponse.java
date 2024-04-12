package com.patika.garantiservice.dto.response;

import com.patika.garantiservice.enums.LoanType;
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
