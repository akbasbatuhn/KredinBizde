package com.patika.bankservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.patika.bankservice.enums.LoanType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "bank_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"loanList", "handler","hibernateLazyInitializer"}, allowSetters = true)
    private Bank bank;

    private Double interestRate;
    private LoanType loanType;
    private Long loanIdFromBank;
}
