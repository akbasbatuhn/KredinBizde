package com.patika.applicationservice.dto.client.response;

import com.patika.applicationservice.enums.LoanType;
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
public class LoanDetailsResponse {

    private Long id;
    private Long bankId;
    private Double interestRate;
    private LoanType loanType;
    private Long loanIdFromBank;
}
