package com.patika.garantiservice.entity;

import com.patika.garantiservice.enums.ApplicationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Loan loan;

    private BigDecimal amount;
    private BigDecimal repaymentAmount;
    private int installment;
    private LocalDateTime applicationDate;
    private ApplicationStatus applicationStatus;

    public void calculateRepaymentAmount() {
        Double interestRate = loan.getInterestRate();
        BigDecimal repayment = amount.divide(new BigDecimal(100), RoundingMode.HALF_UP);
        repaymentAmount = repayment.multiply(BigDecimal.valueOf(interestRate)).multiply(BigDecimal.valueOf(installment)).add(amount);
    }
}
