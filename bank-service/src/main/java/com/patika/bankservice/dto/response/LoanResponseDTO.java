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
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponseDTO {

    private Long id;
    private Long bankId;
    private Double interestRate;
    private LoanType loanType;
    private Long loanIdFromBank;
}
