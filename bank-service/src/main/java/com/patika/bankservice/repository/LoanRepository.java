package com.patika.bankservice.repository;

import com.patika.bankservice.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("SELECT l FROM Loan l WHERE l.loanIdFromBank = :loanIdFromBank AND l.bank.id = :bankId")
    Loan findByLoanIdFromBankAndBankId(@Param("loanIdFromBank") Long loanIdFromBank, @Param("bankId") Long bankId);

}
