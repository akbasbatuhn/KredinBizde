package com.patika.applicationservice.dto.client.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankApplicationRequest {

    private Long loanId;
    private BigDecimal amount;
    private int installment;
}
