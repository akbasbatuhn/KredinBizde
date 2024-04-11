package com.patika.akbankservice.service;

import com.patika.akbankservice.converter.LoanConverter;
import com.patika.akbankservice.dto.request.LoanRequest;
import com.patika.akbankservice.dto.response.LoanResponse;
import com.patika.akbankservice.entity.Loan;
import com.patika.akbankservice.repository.LoanRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LoanService {

    private final LoanRepository repository;
    private final LoanConverter loanConverter;

    public LoanService(LoanRepository repository, LoanConverter loanConverter) {
        this.repository = repository;
        this.loanConverter = loanConverter;
    }

    @Transactional
    public LoanResponse createLoan(LoanRequest request) {
        Loan loan = repository.save(loanConverter.toLoan(request));
        return loanConverter.toResponse(loan);
    }

    public List<LoanResponse> getAll() {
        return repository.findAll().stream()
                .map(loan -> loanConverter.toResponse(loan))
                .toList();
    }

    public LoanResponse getLoanById(Long id) {
        Loan loan = findLoanById(id);

        log.info("Application found with id: {}", id);
        return loanConverter.toResponse(loan);
    }

    public Loan findLoanById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found with id " + id)
        );
    }

    @Transactional
    public LoanResponse updateLoan(Loan request) {
        Loan found = findLoanById(request.getId());

        found.setLoanType(request.getLoanType());
        found.setInterestRate(request.getInterestRate());

        Loan newApplication = repository.save(found);

        return loanConverter.toResponse(newApplication);
    }

    @Transactional
    public void deleteLoanById(Long id) {
        Loan found = findLoanById(id);

        repository.delete(found);
        log.info("Application deleted with id: {}", id);
    }
}
