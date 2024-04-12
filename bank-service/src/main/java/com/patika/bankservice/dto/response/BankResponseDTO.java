package com.patika.bankservice.dto.response;

import com.patika.bankservice.entity.Loan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankResponseDTO {

    private Long id;
    private String name;
    private List<Loan> loanList;
}
