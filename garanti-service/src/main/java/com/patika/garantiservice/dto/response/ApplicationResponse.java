package com.patika.garantiservice.dto.response;

import com.patika.garantiservice.enums.ApplicationStatus;
import com.patika.garantiservice.enums.LoanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApplicationResponse {

    private Long id;
    private LocalDateTime applicationDate;
    private ApplicationStatus applicationStatus;
    private BigDecimal amount;
    private BigDecimal repaymentAmount;
    private int installment;
    private LoanType loanType;
}
