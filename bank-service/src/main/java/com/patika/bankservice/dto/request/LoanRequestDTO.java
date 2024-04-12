package com.patika.bankservice.dto.request;

import com.patika.bankservice.enums.LoanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequestDTO {

    private Long bankId;
    private Double interestRate;
    private LoanType loanType;
}
