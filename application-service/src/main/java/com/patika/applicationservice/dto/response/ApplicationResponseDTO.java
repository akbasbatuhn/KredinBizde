package com.patika.applicationservice.dto.response;

import com.patika.applicationservice.enums.ApplicationStatus;
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
public class ApplicationResponseDTO {

    private Long id;
    private Long loanId;
    private String userId;
    private ApplicationStatus applicationStatus;
    private BigDecimal amount;
    private int installment;
}
