package com.patika.akbankservice.repository;

import com.patika.akbankservice.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
